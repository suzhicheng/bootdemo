package com.bootdemo.system.system.user.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bootdemo.common.config.SystemConfig;
import com.bootdemo.common.enums.CommonEnum;
import com.bootdemo.common.utils.FileType;
import com.bootdemo.common.utils.FileUtil;
import com.bootdemo.common.utils.ImageUtils;
import com.bootdemo.common.utils.MD5Utils;
import com.bootdemo.common.utils.UUIDUtils;
import com.bootdemo.system.common.file.entity.FileEntity;
import com.bootdemo.system.common.file.service.FileService;
import com.bootdemo.system.common.log.service.LogService;
import com.bootdemo.system.system.user.entity.UserEntity;
import com.bootdemo.system.system.user.entity.UserVO;
import com.bootdemo.system.system.user.mapper1.UserMapper;
import com.bootdemo.system.system.user.service.UserService;
import com.bootdemo.system.system.userRole.UserRoleService;
import com.bootdemo.system.system.userRole.entity.UserRoleEntity;

//@CacheConfig(cacheNames = "user")
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    private FileService sysFileService;
    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private LogService logService;
    @Autowired
    private UserRoleService userRoleService;
    
    public UserEntity get(String userId) {
        List<String> roleIds = userRoleService.listRoleId(userId);
        UserEntity user = userMapper.selectById(userId);
        user.setRoleIds(roleIds);
        return user;
    }

    
    public List<UserEntity> list(Map<String, Object> map) {
        return userMapper.list(map);
    }

    
    public int count(Map<String, Object> map) {
        return userMapper.count(map);
    }

    @Transactional
    public int save(UserEntity user) {
        int count = userMapper.insert(user);
        String userId = user.getId();
        List<String> roles = user.getRoleIds();
        userRoleService.removeByUserId(userId);
        List<UserRoleEntity> list = new ArrayList<>();
        for (String roleId : roles) {
            UserRoleEntity ur = new UserRoleEntity();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            ur.setId(UUIDUtils.getUUID());
            ur.setCreateDate(new Date());
            list.add(ur);
        }
        if (list.size() > 0) {
        	userRoleService.batchSave(list);
        }
        return count;
    }

    
    public int update(UserEntity user) {
        int r = userMapper.updateById(user);
        String userId = user.getId();
        List<String> roles = user.getRoleIds();
        userRoleService.removeByUserId(userId);
        List<UserRoleEntity> list = new ArrayList<>();
        for (String roleId : roles) {
            UserRoleEntity ur = new UserRoleEntity();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            ur.setId(UUIDUtils.getUUID());
            ur.setCreateDate(new Date());
            list.add(ur);
        }
        if (list.size() > 0) {
        	userRoleService.batchSave(list);
        }
        return r;
    }

    
    public int remove(String userId) {
        userRoleService.removeByUserId(userId);
        //根据用户id删除日志
        logService.removeByUserId(userId);
        userRoleService.removeByUserId(userId);
        return userMapper.deleteById(userId);
    }

    
    public boolean exit(Map<String, Object> params) {
        boolean exit;
        exit = userMapper.list(params).size() > 0;
        return exit;
    }

    
    public Set<String> listRoles(Long userId) {
        return null;
    }

    
    public int resetPwd(UserVO userVO, UserEntity userDO) throws Exception {
        if (Objects.equals(userVO.getUserDO().getId(), userDO.getId())) {
            if (Objects.equals(MD5Utils.encode(userVO.getPwdOld()), userDO.getPassword())) {
                userDO.setPassword(MD5Utils.encode(userVO.getPwdNew()));
                return userMapper.updateById(userDO);
            } else {
                throw new Exception("输入的旧密码有误！");
            }
        } else {
            throw new Exception("你修改的不是你登录的账号！");
        }
    }

    
    public int adminResetPwd(UserVO userVO) throws Exception {
        UserEntity userDO = get(userVO.getUserDO().getId());
        if ("admin".equals(userDO.getUserName())) {
            throw new Exception("超级管理员的账号不允许直接重置！");
        }
        userDO.setPassword(MD5Utils.encode(userVO.getPwdNew()));
        return userMapper.updateById(userDO);


    }

    @Transactional
    public int batchRemove(String[] userIds) {
        int count = userMapper.deleteBatchIds(Arrays.asList(userIds));
        userRoleService.batchRemoveByUserId(userIds);
        return count;
    }

    

    
    public int updatePersonal(UserEntity userDO) {
        return userMapper.updateById(userDO);
    }

    
    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, String userId) throws Exception {
    	String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
        FileEntity sysFile = new FileEntity(null,FileType.fileType(fileName), "/files/user/" + fileName, new Date(),CommonEnum.YES.getCode());
        //获取图片后缀
        String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
        String[] str = avatar_data.split(",");
        //获取截取的x坐标
        int x = (int) Math.floor(Double.parseDouble(str[0].split(":")[1]));
        //获取截取的y坐标
        int y = (int) Math.floor(Double.parseDouble(str[1].split(":")[1]));
        //获取截取的高度
        int h = (int) Math.floor(Double.parseDouble(str[2].split(":")[1]));
        //获取截取的宽度
        int w = (int) Math.floor(Double.parseDouble(str[3].split(":")[1]));
        //获取旋转的角度
        int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
        try {
            BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
            BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(rotateImage, prefix, out);
            //转换后存入数据库
            byte[] b = out.toByteArray();
            FileUtil.uploadFile(b, systemConfig.getUploadPath()+"/user/headImg/", fileName);
        } catch (Exception e) {
            throw new Exception("图片裁剪错误！！");
        }
        Map<String, Object> result = new HashMap<>();
        if (sysFileService.insert(sysFile)) {
            UserEntity userDO = new UserEntity();
            userDO.setId(userId);
            userDO.setPicId(sysFile.getId());
            if (userMapper.updateById(userDO) > 0) {
                result.put("url", sysFile.getUrl());
            }
        }
        return result;
    }

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
	public List<UserEntity> listByPhone(String phone) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("phone", phone);
		return userMapper.listByPhone(params);
	}

}
