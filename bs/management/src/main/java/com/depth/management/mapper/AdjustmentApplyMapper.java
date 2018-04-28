package com.depth.management.mapper;

import com.depth.management.model.AdjustmentApply;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AdjustmentApplyMapper extends Mapper<AdjustmentApply> {

    List<AdjustmentApply> findByOrigin(Long departmentId);

    List<AdjustmentApply> findByArrive(Long departmentId);
}
