package com.anh.foodsupplybe.service.impl;

import com.anh.foodsupplybe.dto.MemberLevelDto;
import com.anh.foodsupplybe.model.MemberLevel;
import com.anh.foodsupplybe.repo.MemberLevelRepository;
import com.anh.foodsupplybe.service.MemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "memberLevelService")
public class MemberLevelServiceImpl implements MemberLevelService {

    @Autowired
    private MemberLevelRepository memberLevelRepository;
    @Override
    public Optional<MemberLevel> getMemberLevel(Long userId) {
        return memberLevelRepository.findById(userId) ;
    }

    @Override
    public MemberLevel saveMemberLevel(MemberLevelDto memberLevelDto) {
        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setName(memberLevelDto.getName());
        memberLevel.setMinSpent(memberLevelDto.getMinSpent());
        memberLevel.setDiscountRate(memberLevelDto.getDiscountRate());
        memberLevel.setMinPurchases(memberLevelDto.getMinPurchases());
        memberLevelRepository.save(memberLevel);
        return memberLevel;
    }
}

