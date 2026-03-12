# UMC-9th-Spring-Study

## 📝 깃 가이드


### 📤 프로젝트 열기
* 프로젝트를 하나 생성하고 IntelliJ에서 원격 저장소를 연결해줍니다
* ```git init``` // 저장소 생성
* ```git remote add origin https://github.com/HIUMC/UMC-10th-Spring-Study.git```  //저장소 내 컴퓨터 <-> 원격 연결
* ```git pull origin main```
* IntelliJ의 terminal에서 자신의 branch로 checkout을 해주세요
    * ```git checkout origin [본인 github 아이디]```  //브랜치 개념 모른다면 구글링. 자신만의 공간을 만든다고 생각하시면 됩니다.
* 작업은 해당 **본인 branch에서만** 진행해주세요 **(~~❌main branch❌~~)**
    * ```git branch``` : 현재 branch 확인

위의 방식이 어렵다면 깃 Clone 하는 방법을 검색해보기

### 🏡 작업공간 생성
* UMC-10th-Spring-Study (프로젝트 루트 디렉토리)
    * 본인 github 아이디명의 디렉토리 (본인 github 아이디로 설정해주세요!)
        * 해당 강의 디렉토리나 워크북명 (강의 이름으로 설정해주세요!)
            * 스프링 소스코드 or 워크북 공부하면서 배운 것 등등...


| 본인 github 아이디명의 디렉토리부터 만들고 시작하시면 됩니다! 😊
```
│
├─ UMC-10th-Spring-Study
│     │
│     ├─ github 아이디명 (dir)
│     │     │ 
|     |     |
│     │     ├─  스프링 입문 (dir)
│     │     │    ├─ gradle/wrapper
│     │     │    ├─ src
│     │     │    ├─ .gitignore
│     │     │    ├─ build.gradle
│     │     │    ├─ gradlew
│     │     │    ├─ gradlew.bat
│     │     │    └─ settings.gradle
│     │     │
│     │     ├─ 스프링 핵심 원리 - 기본편 (dir)
│     │     │    └─ .. 이하 동일
│     │     │
│     │     └─ 스프링 부트와 JPA 활용1 (dir)
│     │          └─ .. 이하 동일 
│     │ 
│     │      //예시
│     ├─ LeeJeongHeon02 (dir)
│     │     │ 
|     |     |
│     │     ├─  스프링 입문 (dir)
│     │     │    ├─ gradle/wrapper
│     │     │    ├─ src
│     │     │    ├─ .gitignore
│     │     │    ├─ build.gradle
│     │     │    ├─ gradlew
│     │     │    ├─ gradlew.bat
│     │     │    └─ settings.gradle
│     │     │
│     │     ├─ 스프링 핵심 원리 - 기본편 (dir)
│     │     │    └─ .. 이하 동일
│     │     │
│     │     └─ 스프링 부트와 JPA 활용1 (dir)
│     │          └─ .. 이하 동일
│     │   
│     │   
│     ├─ .. 이하 동일
│ 
│ 
```

### 💫 .gitignore에 필요없는 각종 설정파일 추가하기
* 깃허브에 코드 처음 커밋하고 푸시해주실때 본인 브랜치 가장 상위에는 ```본인 깃허브 브랜치 이름으로 된 디렉토리```만 해주셔야 합니다
* ```.gitigore``` 에 필요없는 각종 설정 파일들 추가해주셔서 ```최상위 디렉토리```,```.gitignore```, ```README.md``` 제외 다른 파일들은 푸시 안되게 해주셔야 합니다
* 보통 ```.idea``` 디렉토리나 ```.DS_Store(맥북의 경우)``` 이런 파일들이 추가로 올라가있는데 이런거 전부 최상위 디렉토리와 같은 레벨에 .gitignore 파일 만드시고   
  .idea/   
  .DS_Store   
  이렇게 추가해주시면 됩니다!!

### 💾 중간 중간 commit 하기
* 파트 별로 작업을 끝냈거나 더 작은 단위로 작업을 끝낼 때 마다 commit을 해 주는 게 좋아요
* ❗commit convention ❗️
    * **Feat**: 새로운 기능 추가
    * **Fix**: 버그 수정
    * **Docs**: 문서 수정
    * **Style**: 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
    * **Refactor**: 코드 리펙토링
    * **Test**: 테스트 코드, 리펙토링 테스트 코드 추가
    * **Chore**: 빌드 업무 수정, 패키지 매니저 수정

* 예시: ```git commit -m "[Feat(본인 이름(닉네임)): OO 기능 추가]"```

### 🙌🏻 Github 레포지토리에 push하기

* 해당 주차의 강의를 듣거나 작업을 모두 끝냈다면, 프로젝트 변경 사항을 remote repository(github repository)에 push 합니다
    * ```git push origin [본인 github 아이디 브랜치 명]``` : git에 등록되어 있는 origin(github repository)에 있는 자신의 branch로 프로젝트의 변경 사항을 반영합니다
* push를 완료했다면 스터디 repository에서 pull request를 진행합니다
    * 링크: https://github.com/HIUMC/UMC-10th-Spring-Study/pulls
* PR(Pull Request)시 메세지 제목은 다음과 같이 ```[이름/닉네임] 1주차 정리내용 제출.``` 라고 적은 후 ```create pull request``` 버튼을 눌러주세요
* PR : ```base: [본인 해당 class 브랜치]``` <- ```compare: [본인 github 아이디]```

