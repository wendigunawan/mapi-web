package com.mapi.ihrd.module.absence.service.impl;

import com.mapi.ihrd.module.absence.dao.ShiftDao;
import com.mapi.ihrd.module.absence.model.QShift;
import com.mapi.ihrd.module.absence.model.Shift;
import com.mapi.ihrd.module.absence.queryfilter.ShiftQueryFilter;
import com.mapi.ihrd.module.absence.service.ShiftService;
import com.mapi.ihrd.util.DateUtil;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Service
@Transactional
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    ShiftDao shiftDao;

    @Override
    public void save(Shift shift) {
        shift.setCreatedDate(DateUtil.now());
        shiftDao.save(shift);
    }

    @Override
    public Shift getById(String id) {

        QShift shift = QShift.shift;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(shift.deleted.eq(false)).and(shift.id.eq(id));

        return shiftDao.findOne(booleanBuilder);
    }

    @Override
    public Shift getByShiftId(String id) {
        QShift shift = QShift.shift;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(shift.deleted.eq(false)).and(shift.shiftId.eq(id));

        return shiftDao.findOne(booleanBuilder);
    }

    @Override
    public Iterable<Shift> find() {
        QShift shift = QShift.shift;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(shift.deleted.eq(false));
        return shiftDao.findAll(booleanBuilder);
    }

    @Override
    public Page<Shift> find(ShiftQueryFilter filter) {
        QShift shift = QShift.shift;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(shift.deleted.eq(false));

        if (StringUtils.hasLength(filter.getSearchText())) {
            booleanBuilder.and(shift.shiftId.likeIgnoreCase(filter.getSearchText().toLowerCase()));
        }

        return shiftDao.findAll(booleanBuilder, filter.getPageRequest());
    }
}
