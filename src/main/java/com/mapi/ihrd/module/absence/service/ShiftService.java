package com.mapi.ihrd.module.absence.service;

import com.mapi.ihrd.module.absence.model.Shift;
import com.mapi.ihrd.module.absence.queryfilter.ShiftQueryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface ShiftService {

    void save(Shift shift);

    Shift getById(String id);

    Shift getByShiftId(String id);

    Iterable<Shift> find();

    Page<Shift> find(ShiftQueryFilter filter);
}
