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

import com.furan.living.commodity.entity.HealthInfoEntity;
import com.furan.living.commodity.service.HealthInfoService;




/**
 * 健康信息记录表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
@RestController
@RequestMapping("commodity/healthinfo")
public class HealthInfoController {
    @Autowired
    private HealthInfoService healthInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = healthInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		HealthInfoEntity healthInfo = healthInfoService.getById(id);

        return R.ok().put("healthInfo", healthInfo);
    }

    @RequestMapping("/save")
    public R save(@RequestBody HealthInfoEntity healthInfo){
        // 校验血压格式和数值
        String bp = healthInfo.getBloodPressure();
        if (bp == null || !bp.matches("\\d{1,3}/\\d{1,3}")) {
            return R.error("血压格式必须是XX/XX的形式");
        }
        String[] parts = bp.split("/");
        int systolic = Integer.parseInt(parts[0]);
        int diastolic = Integer.parseInt(parts[1]);
        if (systolic >= 200 || diastolic >= 200) {
            return R.error("血压两边数值必须小于200");
        }

        // 校验睡眠时间
        Integer sleepHours = healthInfo.getSleepHours();
        if (sleepHours == null || sleepHours >= 24) {
            return R.error("睡眠时间必须小于24小时");
        }

        // 校验体重
        Integer weight = healthInfo.getWeight();
        if (weight == null || weight >= 500) {
            return R.error("体重必须小于500kg");
        }

        healthInfoService.save(healthInfo);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody HealthInfoEntity healthInfo){
        // 同样校验
        String bp = healthInfo.getBloodPressure();
        if (bp == null || !bp.matches("\\d{1,3}/\\d{1,3}")) {
            return R.error("血压格式必须是XX/XX的形式");
        }
        String[] parts = bp.split("/");
        int systolic = Integer.parseInt(parts[0]);
        int diastolic = Integer.parseInt(parts[1]);
        if (systolic >= 200 || diastolic >= 200) {
            return R.error("血压两边数值必须小于200");
        }

        Integer sleepHours = healthInfo.getSleepHours();
        if (sleepHours == null || sleepHours >= 24) {
            return R.error("睡眠时间必须小于24小时");
        }

        Integer weight = healthInfo.getWeight();
        if (weight == null || weight >= 500) {
            return R.error("体重必须小于500kg");
        }

        healthInfoService.updateById(healthInfo);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		healthInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
