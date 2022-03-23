package jpabook.jpashop.service;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest   //이 2개가있어야 스프링부트와 인티그레이션해서 테스트가능
@Transactional // 데이터를 변경해야하기때문에 이게 있어야 롤백가능
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("kim");
        //When
        Long saveId = memberService.join(member);
        //Then
        assertEquals(member, memberRepository.findOne(saveId));//이게 가능한 이유는 transational 때문
                                                               //각 jpa에서 같은 transactional 안에서 같은 엔티티(id값이 똑같으면)
                                                               //같은 영속성 컨텍스트에서 똑같은 것으로 관리됨  딱 하나로만
    }


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_체크() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다.
        //then
        fail("예외가 발생해야 한다.");
    }

}