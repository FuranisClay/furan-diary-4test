package com.furan.living.commodity.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

import com.furan.living.commodity.utils.PageUtils;
import com.furan.living.commodity.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.furan.living.commodity.entity.LifeRecordEntity;
import com.furan.living.commodity.service.LifeRecordService;



/**
 * 生活记录表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
@RestController
@RequestMapping("commodity/liferecord")
public class LifeRecordController {
    @Autowired
    private LifeRecordService lifeRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = lifeRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		LifeRecordEntity lifeRecord = lifeRecordService.getById(id);

        return R.ok().put("lifeRecord", lifeRecord);
    }


    @RequestMapping("/save")
    public R save(@RequestBody LifeRecordEntity lifeRecord){
        String imageUrl = lifeRecord.getImageUrl();
        if (!isValidUrl(imageUrl)) {
            return R.error("图片路径必须是合法的网络地址");
        }
        lifeRecordService.save(lifeRecord);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody LifeRecordEntity lifeRecord){
        String imageUrl = lifeRecord.getImageUrl();
        if (!isValidUrl(imageUrl)) {
            return R.error("图片路径必须是合法的网络地址");
        }
        lifeRecordService.updateById(lifeRecord);
        return R.ok();
    }

    /** 校验是否为合法网络地址 */
    private boolean isValidUrl(String url){
        if (url == null || url.isEmpty()) return false;
        try {
            URL u = new URL(url);
            String protocol = u.getProtocol();
            return protocol.equals("http") || protocol.equals("https");
        } catch (MalformedURLException e) {
            return false;
        }
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		lifeRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
