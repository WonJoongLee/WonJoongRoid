
# 1주차  
**화면 전환 시 데이터 송수신 로직**  
  
![reg](https://user-images.githubusercontent.com/57510192/114303454-2c967180-9b09-11eb-8457-bd4d2a301b3e.PNG)
  
  
- SignInActivity 코드  
```  
private val signUpActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val data = it.data
                data?.let {
                    binding.etGithubId.setText(data.getStringExtra("githubID").toString())
                    binding.etGithubPw.setText(data.getStringExtra("pw").toString())
                }

            }
        }
```  
registerForActivityResult 함수 내에서 입력을 받아 다시 처리하는 부분까지 모두 관여한다. SignUpActivity에서 RESULT_OK라고 값을 넘겨줄 때만만 editText의 값을 변경해준다.
원래는 startActivityForResult를 통해 입력값을 받아왔으나, Activity 당 처리해야하는 바람에 코드 수가 길어지고 복잡해져 deprecated되고, `registerForActivityResult`를 사용한다.
<br>  
- SignUpActivity 코드  

```
val intent = Intent(this, SignUpActivity::class.java)  
intent.putExtra("githubID", binding.etGithubId.text.toString())  
intent.putExtra("pw", binding.etGithubPw.text.toString())  
setResult(Activity.RESULT_OK, intent)  
finish()
```

`putExtra`를 통해 원하는 값을 돌려줄 수 있다.
`startActivity`메서드가 아닌 `setResult`메서드와 `finish`메서드를 통해 돌려줄 값을 설정하고, Activity를 종료한다.
<br>  
  
**화면 전환 시 생명 주기 변화**  
SignInActivity에서 HomeActivity로 이동했을 때 생명주기의 변화 Logcat.  
![qqqq](https://user-images.githubusercontent.com/57510192/113586447-7cd48600-9668-11eb-80e4-7687b0bef792.PNG)  
앱이 실행되고 SignInActivity 액티비티가 실행되어 스크린에 나오면,   
`onCreate()`->`onStart()`->`onResume()` 순서로 호출 됨.  
이후 HomeActivity로 이동하면, 기존에 화면에 보이던 SignInActivity는 `onPause()`되고, HomeActivity 또한 `onStart()`->`onResume()`->`onPause()` 순서대로 호출이 된다. 이후에 SignInActivity는 화면에서 더이상 보이지 않으므로 `onStop()`이 호출된다.  
  
<br>  
  
**배운 내용**  
- `onCreate()`외부에서 Button Click Listener가 된다는 것.
- `constraintDimensionRatio`를 통한 사진 비율 조정.
- 생명주기 호출하는 법.
- `startActivityForResult()`가 deprecated 되었다는 것.
- xml에서 chain을 사용하는 법. (`chainstyle`)
- xml에서 `verticalbias`, `horizontalbias`.
