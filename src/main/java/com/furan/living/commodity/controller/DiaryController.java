package com.furan.living.commodity.controller;

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

import com.furan.living.commodity.entity.DiaryEntity;
import com.furan.living.commodity.service.DiaryService;




/**
 * 日记表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
@RestController
@RequestMapping("commodity/diary")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = diaryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		DiaryEntity diary = diaryService.getById(id);

        return R.ok().put("diary", diary);
    }

    @RequestMapping("/save")
    public R save(@RequestBody DiaryEntity diary){
        String weather = diary.getWeather();
        if (weather == null || !(weather.equals("晴") || weather.equals("雨") || weather.equals("阴"))) {
            return R.error("天气只能是晴、雨、阴");
        }
        diaryService.save(diary);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody DiaryEntity diary){
        String weather = diary.getWeather();
        if (weather == null || !(weather.equals("晴") || weather.equals("雨") || weather.equals("阴"))) {
            return R.error("天气只能是晴、雨、阴");
        }
        diaryService.updateById(diary);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		diaryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
