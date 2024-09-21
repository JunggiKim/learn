package study.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.entity.Member;
import study.querydsl.entity.MemberSearchCondition;
import study.querydsl.entity.MemberTeamDto;

import java.util.List;

public interface MemberCustomRepository {

        List<MemberTeamDto> search(MemberSearchCondition condition);
        Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);
        Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition,Pageable pageable);




}
