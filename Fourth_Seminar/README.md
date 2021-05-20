# 4주차
### 구동영상
<p align="center">
<img src="https://user-images.githubusercontent.com/57510192/118343551-b7351b00-b564-11eb-9015-fa8fc7ccbfd3.gif" width="300px">
</p>

4주차에서는 `Retrofit2`로 서버와 통신하는 법을 배웠습니다. 
<br>
### 폴더 구조
<p align="center">
<img src="https://user-images.githubusercontent.com/57510192/118217413-619f3680-b4b0-11eb-97c8-5f877e449694.PNG" width="200px">
</p>
 
## Level 1 - 로그인 / 회원가입 통신 구현
레트로핏을 이용해서 로그인과 회원가입 통신을 구현했습니다.
<br>

### 회원가입 구현 부분
Retrofit2를 이용하기 위해서는 ServiceCreator singleton object와 Service Interface를 만들어줘야 합니다.

**ServiceCreator.kt**
```kotlin
object ServiceCreator {  
    private const val SOPT_BASE_URL = "http://cherishserver.com"  
  private const val GITHUB_BASE_URL = "http://api.github.com"  
  private val soptRetrofit: Retrofit =  
        Retrofit.Builder()  
            .baseUrl(SOPT_BASE_URL)  
            .addConverterFactory(GsonConverterFactory.create())  
            .build()  
    private val githubRetrofit: Retrofit =  
        Retrofit.Builder()  
            .baseUrl(GITHUB_BASE_URL)  
            .addConverterFactory(GsonConverterFactory.create())  
            .build()  
    val soptService: SoptService = soptRetrofit.create(SoptService::class.java)  
    val githubService: GithubService = githubRetrofit.create(GithubService::class.java)  
}
```
**SoptService.kt**
```kotlin
interface SoptService {  
    @POST("/login/signin")  
    fun postLogin(  
        @Body body: RequestLoginData  
    ): Call<ResponseLoginData>  
  
    @POST("/login/signup")  
    fun postSignUp(  
        @Body body: RequestSignupData  
    ): Call<ResponseSignupData>  
}
```
아래는 SingUpActivity와 SignInActivity에서 Retrofit을 Call하고 enqueue를 통해 Callback을 받는 코드입니다.
**SignUpActivity.kt**
```kotlin
val requestSignUpData = RequestSignupData(  
    email = binding.etGithubEmail.text.toString(),  
  password = binding.etGithubPw.text.toString(),  
  sex = if (binding.rbMale.isChecked) {  
        "0"  
  } else {  
        "1"  
  },  
  nickname = binding.etGithubNickname.text.toString(),  
  phone = binding.etPhoneNumber.text.toString(),  
  birth = binding.tvBirthdayDate.text.toString()  
)  
val call: Call<ResponseSignupData> =  
    ServiceCreator.soptService.postSignUp(requestSignUpData)  
call.enqueue(object : Callback<ResponseSignupData> {  
    override fun onResponse(  
        call: Call<ResponseSignupData>,  
  response: Response<ResponseSignupData>  
    ) {  
        if (response.isSuccessful) {  
            val data = response.body()?.responseData  
  if (response.body()?.success == true) {  
                toast(data?.nickname ?: "")  
                startSignInActivity()  
            } else {  
                toast("Error")  
            }  
        }  
    }  
    override fun onFailure(call: Call<ResponseSignupData>, t: Throwable) {  
        Log.e("NetworkFailure", "error:$t")  
    }  
})
```

**SigninActivity.kt**
```kotlin
private fun setRepoRv() {  
    val repoList = mutableListOf<RepositoryInfo>()  
    initData(repoList)  
  
    val repoAdapter = RepositoryAdapter(repoList)  
    val repoRecyclerView = binding.rvRepository  
	repoRecyclerView.adapter = repoAdapter  
    repoRecyclerView.setHasFixedSize(false)  
    repoAdapter.notifyDataSetChanged()  
}
```

<br>
<br>

## Level 2 - Github API 이용하기
<p align="center">Github Repository와 Follower 수신 구현</p>
<p align="center">
<img src="https://user-images.githubusercontent.com/57510192/118343963-ddf45100-b566-11eb-9dc0-87db696eda86.PNG" class="inline" width="200px">
<img src="https://user-images.githubusercontent.com/57510192/118343988-f6646b80-b566-11eb-8596-89be684c3f8b.PNG" class="inline" width="200px">
</p>

Level 1과 마찬가지로 Service interface 생성<br>
**GithubService.kt**
```kotlin
interface GithubService {  
    @Headers("Authorization: ${R.string.github_private_Key}")  
    @GET("/users/WonJoongLee/repos")  
    fun getRepos(): Call<List<RepositoryInfo>>  
  
    @Headers("Authorization: ${R.string.github_private_Key}")  
    @GET("/users/WonJoongLee/followers")  
    fun getFollowers(): Call<List<FollowerInfo>>  
}
```
Headers 어노테이션에 github private key를 추가했는데, 하루 Github에서 제공하는 API 사용량 초과시 추가로 private key를 넣으면 더 사용할 수 있게 해줬습니다. `R.string.github_private_key`는 values에 secret_keys.xml을 추가하여 따로 관리하고 `gitignore`파일을 통해 github에 올리지 않았습니다.

**HomeActivity.kt**
```kotlin
private fun getRepos() {  
    val call: Call<List<RepositoryInfo>> = ServiceCreator.githubService.getRepos()  
    call.enqueue(object : Callback<List<RepositoryInfo>> {  
        override fun onResponse(  
            call: Call<List<RepositoryInfo>>,  
  response: retrofit2.Response<List<RepositoryInfo>>  
        ) {  
            if (response.isSuccessful) {  
                response.body()?.let {  
  setRepoRv(it)  
                }  
  } else {  
                Log.e("error", "getRepos() error")  
            }  
        }  
  
        override fun onFailure(call: Call<List<RepositoryInfo>>, t: Throwable) {  
            Log.e("onFailure", t.toString())  
        }  
    })  
}
```
Home Activity에서 Gihtub API를 통해 Repository를 가져오는 부분입니다.<br>

## 기타
### 리사이클러 뷰 간격 띄우기
기존에는 리사이클러 뷰 사이의 간격을 띄우기 위해 리사이클러뷰 아이템에 margin이나 padding을 주는 방법을 사용했는데, 이번에는 `RecyclerView.ItemDecoration()`을 활용해봤습니다.
```kotlin
class RvItemDecoration(private val padding: Int, private val rvType: Int) :  
    RecyclerView.ItemDecoration() {  
    override fun getItemOffsets(  
        outRect: Rect,  
		view: View,  
		parent: RecyclerView,  
		state: RecyclerView.State  
    ) {  
        super.getItemOffsets(outRect, view, parent, state)  
        with(outRect) {  
		when (rvType) {  
          // recyclerview가 repository인 경우  
		  REPO_RV_TYPE -> {  
	        top = padding  
			bottom = padding  
		  }  
          // recyclerview가 follower인 경우  
		  FOLLOWER_RV_TYPE -> {  
	        top = padding  
	        bottom = padding  
	        left = padding  
	        right = padding  
		  }  
        }  
     }  
  }  
  
    companion object {  
      val REPO_RV_TYPE = 1  
	  val FOLLOWER_RV_TYPE = 2  
  }  
}
```
companion object를 public으로 설정해서 어떤 리사이클러 뷰에서 클래스를 호출하는지 인식할 수 있도록 했습니다. 
<br>
### 중요 key들 관리
처음에는 코틀린 클래스를 하나 생성해서 중요 API 키들을 관리하려고 했는데, 그렇게 되면 google map이나 google admob과 같이 manifest.xml에서 키를 호출하기 어렵습니다. 그러므로 res.values에서 string으로 관리하는 것이 좋을 것 같아 `secret_keys.xml`로 중요 key들을 관리합니다. 그리고 해당 파일은 gitignore 파일에서 github에 올라가지 않도록 해야 합니다.
<br>
### 리사이클러 뷰 아이템 디자인 수정
- 테두리를 추가했습니다.
```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"  
  android:shape="rectangle">  
 <stroke  android:width="2dp" android:color="@color/black" />  
 <corners  android:radius="2dp"/>  
</shape>
```
- 리플 효과를 넣었습니다.
```xml
<ripple xmlns:android="http://schemas.android.com/apk/res/android"  
  android:color="@color/black" />
```

### 내장 Assets 사용해보기
```kotlin
private fun setColorStr(str: String): String {  
    val assetManager = binding.tvRepositoryLanguage.context.resources.assets // assets가 root  
    val inputStream = assetManager.open("colors.json")  
    val jsonString = inputStream.bufferedReader().use { it.readText() }  
    val jObject = JSONObject(jsonString)  
    var colorStr = ""  
    jObject.keys().forEach {  
    if (it == str) {  
            colorStr = jObject.get(it).toString()  
    }  
  }  
  return colorStr  
}
```
assets 폴더 아래에 있는 colors.json을 통해 각 레포지토리 언어에 색을 추가했습니다.

## 4주차 이원중 패치 내역
1. 서버와 통신하는 법을 몰랐었는데, 레트로핏을 통해 서버와 통신하는 기초를 학습했습니다.
2. interface, singleton object이 무엇인지와 쓰는 방법을 학습했습니다.
3. API 사용하는 법을 학습했습니다.
4. drawable xml을 통해 버튼 배경과 리플 효과를 추가해보았지만, 학습을 더 해야 합니다.
5. 내장 assets을 사용해봤지만, 처음 써보기도 했고 구글링해서 쓴 코드이기 때문에 많은 학습이 요구됩니다.
