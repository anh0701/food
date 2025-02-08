package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.dto.MemberLevelDto;
import com.anh.foodsupplybe.model.MemberLevel;

import java.util.Optional;

public interface MemberLevelService {
    Optional<MemberLevel> getMemberLevel(Long userId);

    MemberLevel saveMemberLevel(MemberLevelDto memberLevelDto);
}
