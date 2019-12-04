package com.ppdai.das.console.cloud.controller;

import com.google.common.collect.Lists;
import com.ppdai.das.console.cloud.dao.DataBaseCloudDao;
import com.ppdai.das.console.cloud.dto.model.ServiceResult;
import com.ppdai.das.console.cloud.dto.view.DataBaseView;
import com.ppdai.das.console.cloud.service.DatabaseCloudService;
import com.ppdai.das.console.dto.entry.das.DataBaseInfo;
import com.ppdai.das.console.dto.model.Item;
import com.ppdai.das.console.enums.DataBaseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/das/db")
public class DatabaseCloudController {

    @Autowired
    private DataBaseCloudDao dataBaseCloudDao;
    @Autowired
    private DatabaseCloudService databaseCloudService;


    @RequestMapping(value = "/getdbList")
    public ServiceResult<List<DataBaseView>> getdbnames(@RequestParam("appid") String appid) throws Exception {
        List<DataBaseView> list = dataBaseCloudDao.getAllDbByAppId(appid);
        return ServiceResult.success(list);
    }

    @RequestMapping(value = "/getdbTypeList")
    public ServiceResult<List<DataBaseView>> getdbTypeList() throws Exception {
        List<Item> list = Lists.newArrayList(new Item(Long.valueOf(DataBaseEnum.MYSQL.getType()), DataBaseEnum.MYSQL.getName()), new Item(Long.valueOf(DataBaseEnum.SQLSERVER.getType()), DataBaseEnum.SQLSERVER.getName()));
        return ServiceResult.success(list);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServiceResult<String> add(@RequestBody DataBaseInfo dataBaseInfo, Errors errors) throws Exception {
        return databaseCloudService.addDataBase(dataBaseInfo, "wangliang", errors);
    }


}
