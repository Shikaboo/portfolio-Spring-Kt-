package com.example.demo.domain

import com.example.demo.domain.constant.SkillType
import com.example.demo.domain.entity.*
import com.example.demo.domain.repository.*
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["default"])
class DataInitializer(
    private val achievementRepository: AchievementRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val skillRepository: SkillRepository,
    private val projectRepository: ProjectRepository,
    private val experienceRepository: ExperienceRepository

) {

    @PostConstruct
    fun initializeData() {

        println("스프링이 실행되었습니다. 테스트 데이터를 초기화 합니다.")

        val achievements = mutableListOf<Achievement>(
            Achievement(
                title = "2022 Catkao 해커톤 최우수상",
                description = "고양이 쇼핑몰 검색 서비스의 아키텍처, 데이터 모델링, API 개발 역할 수행",
                host = "캣카오",
                achievedDate = LocalDate.of(2022, 8, 1),
                isActive = true
            ),
            Achievement(
                title = "정보처리기사",
                description = "자료구조, 운영체제, 알고리즘, 데이터베이스 등",
                host = "한국산업인력공단",
                achievedDate = LocalDate.of(2020, 2, 2),
                isActive = true
            )
        )
        achievementRepository.saveAll(achievements)

        val introductions = mutableListOf<Introduction>(
            Introduction(content = "주도적으로 문제를 찾고, 해결하는 고양이입니다.", isActive = true),
            Introduction(content = "기술을 위한 기술이 아닌, 비즈니스 문제를 풀기 위한 기술을 추구합니다.", isActive = true),
            Introduction(content = "기존 소스를 리팩토링하여 더 좋은 구조로 개선하는 작업을 좋아합니다.", isActive = true)
        )
        introductionRepository.saveAll(introductions)

        val links = mutableListOf<Link>(
            Link(name = "Github", content = "https://github.com/infomuscle", isActive = true),
            Link(name = "Linkedin", content = "https://www.linkedin.com/in/bokeunjeong", isActive = true),
        )
        linkRepository.saveAll(links)

        val experience1 = Experience(
            title = "캣홀릭대학교(CatHolic Univ.)",
            description = "컴퓨터공학 전공",
            startYear = 2018,
            startMonth = 9,
            endYear = 2022,
            endMonth = 8,
            isActive = true
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(content = "GPA 4.3/4.5", isActive = true),
                ExperienceDetail(content = "소프트웨어 연구 학회 활동", isActive = true)
            )
        )
        val experience2 = Experience(
            title = "주식회사 캣카오(Catkao Corp.)",
            description = "소셜서비스팀 백엔드 개발자",
            startYear = 2022,
            startMonth = 9,
            endYear = null,
            endMonth = null,
            isActive = true
        )
        experience2.addDetails(
            mutableListOf(
                ExperienceDetail(content = "유기묘 위치 공유 서비스 개발", isActive = true),
                ExperienceDetail(content = "신입 교육 프로그램 우수상 수상", isActive = true)
            )
        )
        experienceRepository.saveAll(mutableListOf(experience1, experience2))

        Skill(name = "Jave", type = SkillType.LANGUAGE.name, isActive = true)

    }

}
