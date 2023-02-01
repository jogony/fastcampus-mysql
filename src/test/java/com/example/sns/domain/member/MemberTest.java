package com.example.sns.domain.member;

import com.example.sns.domain.member.entity.Member;
import com.example.sns.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    public void testChangeName() {
        Member member = MemberFixtureFactory.create();
        String expected = "pnu";

        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }

    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    @Test
    public void testNickNameMaxLength() {
        Member member = MemberFixtureFactory.create();
        String overMaxLength = "pnupnupnupnu";

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> member.changeNickname(overMaxLength)
        );

    }
}
