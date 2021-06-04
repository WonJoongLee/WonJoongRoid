﻿# 4주차
### 구동영상
<p align="center">
<img src="https://i.postimg.cc/4yYgKgcS/seventh.gif)](https://postimg.cc/fSQFF1Bc" width="300px">
</p>

7주차에서는 `Activity`에 대해 더 배우고, `Shared Preference`에 대해 배웠습니다.
<br>
### 폴더 구조
<p align="center">
<img src="https://user-images.githubusercontent.com/57510192/120729689-c03d5a80-c51a-11eb-966c-f4c03c068090.PNG" width="200px">
</p>

- data폴더 아래에 `SoptUserAuthStorage`가 추가되었습니다. 
 
## Level 1 - 자동 로그인 구현, 로그아웃 구현
`SharedPreferences`를 활용하여 자동 로그인을 구현하였습니다.
<br>

### 자동 로그인
**ServiceCreator.kt**
```kotlin
package org.sopt.androidseminar.data  
  
import android.content.Context  
  
object SoptUserAuthStorage {  
  private const val STORAGE_KEY = "user_auth"  
  private const val USER_ID = "id"  
  private const val USER_PASSWORD = "password"  
  
  fun saveUserId(context: Context, id: String) {  
        val sharedPreferences = context.getSharedPreferences(  
            "${context.packageName}.$STORAGE_KEY", Context.MODE_PRIVATE  
  )  
        sharedPreferences.edit()  
            .putString(USER_ID, id)  
            .apply()  
    }  
  
    fun saveUserPw(context: Context, pw: String) {  
        val sharedPreferences = context.getSharedPreferences(  
            "${context.packageName}.$STORAGE_KEY", Context.MODE_PRIVATE)  
        sharedPreferences.edit()  
            .putString(USER_PASSWORD, pw)  
            .apply()  
    }  
  
    private fun getUserId(context: Context): String {  
        val sharedPreferences = context.getSharedPreferences(  
            "${context.packageName}.$STORAGE_KEY",Context.MODE_PRIVATE)  
        return sharedPreferences.getString(USER_ID, "") ?: ""  
	  }  
  
    private fun getUserPw(context: Context): String {  
        val sharedPreferences = context.getSharedPreferences(  
            "${context.packageName}.$STORAGE_KEY", Context.MODE_PRIVATE)  
        return sharedPreferences.getString(USER_PASSWORD, "") ?: ""  
	}  
  
    fun clearAuthStorage(context: Context) {  
        val sharedPreferences = context.getSharedPreferences(  
            "${context.packageName}.$STORAGE_KEY", Context.MODE_PRIVATE)  
        sharedPreferences.edit().clear().apply()  
    }  
  
    fun hasUserData(context: Context) =  
        getUserId(context).isNotEmpty() && getUserPw(context).isNotEmpty()  
	}
}
```
<br>
<br>

### Activity 관리
**SignInActivity.kt 중 onCreate()**
```Kotlin
override fun onCreate(savedInstanceState: Bundle?) {  
    super.onCreate(savedInstanceState)  
    binding = ActivityMainBinding.inflate(layoutInflater)  
    setContentView(binding.root) 
 // 유저데이터가 있으면 Home Activity 바로 시작  
  if(SoptUserAuthStorage.hasUserData(this)){  
        startHomeActivity()  
        finish()  
    }  
    loginButtonClickEvent()  
    signUpButtonClickEvent()  
    LifecycleObserver(javaClass.simpleName, this.lifecycle).registerLogger()  
}
```
SignInActivity에서 onCreate()가 호출되었을 때, sharedPreference에 유저 데이터가 존재한다면 HomeActivity로 이동합니다. `finish()` 코드를 추가하여 해당 Activity가 스택에 남지 않고 종료될 수 있게 하였습니다.


### 로그아웃
**HomeActivity.kt 중 closeButtonClickEvent()**
```kotlin
private fun closeButtonClickEvent() {  
    binding.ivClose.setOnClickListener {  
		SoptUserAuthStorage.clearAuthStorage(this)  
        val intent = Intent(this, SignInActivity::class.java)  
        startActivity(intent)  
        finish()  
    }  
}
```
사용자가 HomeActivity에서 닫기 버튼을 클릭하면, SharedPreference의 데이터를 삭제하고 화면을 종료합니다. 그 후 로그인 화면인 SignInActivity로 이동하고 현재 Activity를 종료합니다. 뒤로가기 버튼을 눌렀을 때 다시 HomeActivity로 돌아오는 것을 방지하기 위해 stack에서 없애주는 `finish()` 코드를 작성하였습니다.

## 7주차 이원중 패치 내역
1. sharedPreference를 통해 어떻게 자동 로그인하는지 공부하였습니다.
2. Activity stack이 무엇인지 알 수 있었습니다.
3. sharedPreference의 clear 기능에 대해 배웠습니다.

