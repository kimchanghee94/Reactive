package com.example.reactive.chapter15;

import com.example.reactive.common.Member;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.zip.DataFormatException;

public class ErrorExample04 {
    private final static String EXIST_EMAIL = "kevin@gmail.com";
    private final static String NOT_EXIST_EMAIL = "tom@gmail.com";

    public static void main(String[] args){
        //map으로 쓰고 싶은 경우 반환값이 그냥 값이여야한다
        //여기선 SaveMember메서드가 Mono타입으로 반환하기 때문에 flatMap으로 처리한다.
        //그리고 여기 메서드들이 Mono타입으로 감싸서 반환하는 이유는 비동기 컨텍스트를 계속 유지하기 위함이다.
        //saveMember의 반환타입을 Member로 해서 map처리 가능한데 실제 r2dbc는 mono타입으로 반환하기에 flatmap처리가 대부분이다.
        verifyExistMember(EXIST_EMAIL)
            .flatMap(notUse -> saveMember(
                    Member.builder().id(1L).email(NOT_EXIST_EMAIL).name("Kevin").build()
            ))
            .subscribe(
                    createdMember -> Logger.onNext("Created member", createdMember.getEmail()),
                    Logger::onError,
                    Logger::onComplete
            );
    }

    private static Mono<Member> verifyExistMember(String email){
        return selectFromMemberWhere(email)
                .switchIfEmpty(Mono.just(Member.builder().build()))
                .flatMap(foundMember -> {
                    if(foundMember != null && foundMember.getEmail() != null){
                        return Mono.error(new RuntimeException("Member exists"));
                    }
                    return Mono.just(foundMember);
                });
    }

    private static Mono<Member> selectFromMemberWhere(String email){
        Logger.info("# select from member where email = " + email);
        Mono existMember = Mono.just(Member.builder().id(1L).email("kevin@gmail.com").name("Kevin").build());
        return email.equals(EXIST_EMAIL) ? existMember : Mono.empty();
    }

    private static Mono<Member> saveMember(Member member){
        Logger.info("# insert into member...");
        return Mono.just(member);
    }
}
