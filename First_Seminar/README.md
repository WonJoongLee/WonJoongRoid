
# 1주차  
**화면 전환 시 데이터 송수신 로직**  
  
![temp](https://user-images.githubusercontent.com/57510192/113583138-3846eb80-9664-11eb-84b4-333f1f8cce35.PNG)  
  
  
- SignInActivity 코드  
```  
val intent = Intent(this, SignUpActivity::class.java) startActivityForResult(intent, 0)  
```  
`startActivityForResult`를 통해 값을 돌려받음.   
뒤에 Int형 parameter는 단순히 어떤 Activity인지 구별하기 위한 ID값.  
<br>  
- SignUpActivity 코드  
```  
val intent = Intent(this, SignUpActivity::class.java) intent.putExtra("githubID", binding.idET.text.toString()) intent.putExtra("pw", binding.pwET.text.toString()) setResult(Activity.RESULT_OK, intent) finish()  
```  
`putExtra`를 통해 원하는 값을 돌려줄 수 있음.   
`startActivity`메서드가 아닌 `setResult`메서드와 `finish`메서드를 통해 돌려줄 값을 설정하고, Activity를 종료함.  
<br>  
- 다시 값을 받기 위해 SignInActivity 코드  
```  
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {    
    super.onActivityResult(requestCode, resultCode, data)    
    when(requestCode){    
        0->{    
            if(resultCode == RESULT_OK){    
                binding.idET.setText(data!!.getStringExtra("githubID").toString())    
                binding.pwET.setText(data.getStringExtra("pw").toString())    
            }    
        }    
} }  
```  
그러면 다시 값을 돌려받는 Activity에서 `onActivityResult`메서드를 overriding하여 값을 받음. 다른 부분에서 특별한 것은 없고 `requestCode`가 `startActivityForResult`할 때 준 고유 번호 값임을 주의하자.  
  
**화면 전환 시 생명 주기 변화**  
SignInActivity에서 HomeActivity로 이동했을 때 생명주기의 변화 Logcat.  
![qqqq](https://user-images.githubusercontent.com/57510192/113586447-7cd48600-9668-11eb-80e4-7687b0bef792.PNG)  
앱이 실행되고 SignInActivity 액티비티가 실행되어 스크린에 나오면,   
`onCreate()`->`onStart()`->`onResume()` 순서로 호출 됨.  
이후 HomActivity로 이동하면, 기존에 화면에 보이던 SignInActivity는 `onPause()`되고, HomeActivity 또한 `onStart()`->`onResume()`->`onPause()` 순서대로 호출이 된다. 이후에 SignInActivity는 화면에서 더이상 보이지 않으므로 `onStop()`이 호출된다.  
  
<br>  
  
**배운 내용**  
- `onCreate()`외부에서 Button Click Listener가 된다는 것. 특히 버튼 이벤트 함수와 같이 다른 함수 내에서 리스너를 달면 코드가 더 깔끔해지고 미세하게나마 객체지향적 프로그래밍을 할 수 있음.