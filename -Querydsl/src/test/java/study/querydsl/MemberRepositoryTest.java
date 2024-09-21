package study.querydsl;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.MemberSearchCondition;
import study.querydsl.entity.MemberTeamDto;
import study.querydsl.entity.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
class MemberRepositoryTest {


    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;



    @Test
    void search_builder_test(){
        //  given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        //  when
        MemberSearchCondition searchCondition1 = new MemberSearchCondition();
        searchCondition1.setAgeGoe(35);
        searchCondition1.setAgeLoe(40);
        searchCondition1.setTeamName("teamB");


        //  then
        List<MemberTeamDto> result1 = memberRepository.search(searchCondition1);
        Assertions.assertThat(result1).extracting("username").containsExactly("member4");

    }
}