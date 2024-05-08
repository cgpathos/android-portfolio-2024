# Android Portfolio 2024
[![project](https://github.com/cgpathos/android-portfolio-2024/actions/workflows/project.yml/badge.svg?event=push)](https://github.com/cgpathos/android-portfolio-2024/actions/workflows/project.yml)

[![module_common](https://github.com/cgpathos/android-portfolio-2024/actions/workflows/module_common.yml/badge.svg?event=push)](https://github.com/cgpathos/android-portfolio-2024/actions/workflows/module_common.yml)
[![module_data](https://github.com/cgpathos/android-portfolio-2024/actions/workflows/module_data.yml/badge.svg?event=push)](https://github.com/cgpathos/android-portfolio-2024/actions/workflows/module_data.yml)

## 앱 아키텍쳐(TODO)

## 모듈화(TODO)

## CI/CD
CI서버상에서 동작도 중요하지만 배포작업은 시스템을 통하지 않고도 수행해야하는 경우가 종종 발생한다.  
그래서 `fastlane` 적용하고 필요한 lane들을 만들어 CI서버에서 수행하는 작업들을 로컬에서도 수행가능하게 했다.  
CI서버에서 동작도 gradle의 task 대신 fastlane의 lane을 실행하도록 한다.  
`모든 push`, `feature(feat)`, `release` 세단계로 구분한다.

### fastlane

#### test
> gradle test 테스크를 랩핑한 lane.  
> module명을 넘기면 해당 모듈의 unit test만 수행

```zsh
# fastlane test [module:MODULE_NAME]
```

#### build
> 앱파일 빌드 테스트 lane.  
> build_type을 전달해 Debug나 Release 빌드를 결정 (default:Debug)

```zsh
# fastlane build [build_type:{Debug or Release}]
```


### 모든 push
* origin으로 푸시되는 모든 커밋에 대해서 실행
* 변경된 paths를 검사해서 변경된 모듈의 테스트 코드만 실행  
  (지금은 :common, :data에 적용됨)   
* 

### feature(feat) 
* `feature/**` 또는 `feat/**` 브랜치 PR에 대해서 실행
* 빌드 테스트 실행
* 모든 테스트 코드를 실행

### release(TODO)
* 
