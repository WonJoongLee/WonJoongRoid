
# 2주차

### 폴더 구조
![폴더구조](https://user-images.githubusercontent.com/57510192/114294030-30f56700-9ad6-11eb-92f5-7d8625461ac0.PNG)
- **adapters** : Recycler View를 위한 adapter
- **data** : Repository data class
- **ui** : 화면에 띄우는 부분

### 리사이클러 뷰
```
class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {  
  
    var data = mutableListOf<RepositoryInfo>()  
  
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {  
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)  
        return RepositoryViewHolder(view)  
    }  
  
    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {  
        holder.bind(data[position])  
    }  
  
    override fun getItemCount(): Int = data.size  
  
  class RepositoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){  
        private val repoName = itemView.findViewById<TextView>(R.id.tv_repository_name)  
        private val repoInfo = itemView.findViewById<TextView>(R.id.tv_repository_detail)  
        private val repoLang = itemView.findViewById<TextView>(R.id.tv_repository_language)  
        fun bind(repositoryInfo: RepositoryInfo){  
            repoName.text = repositoryInfo.repoName  
 repoInfo.text = repositoryInfo.repoInfo  
 repoLang.text = repositoryInfo.repoLang  
  }  
    }  
}
```
`onCreateViewHolder`, `onBindViewHolder`, `getItemCount`를 overriding하고 `View Holder` class를 통해 리사이클러 뷰 어댑터를 구현할 수 있다.
```
private fun setRepoRv(){  
    var repoList = mutableListOf<RepositoryInfo>()  
    val repoAdapter = RepositoryAdapter()  
    val repoRecyclerView = findViewById<RecyclerView>(R.id.rv_repository)  
    repoList = repoDataInput(repoList)  
    repoAdapter.data = repoList  
    repoRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)  
    repoRecyclerView.adapter = repoAdapter  
    repoRecyclerView.setHasFixedSize(false)  
}
```
리사이클러 뷰를 사용할 액티비티에서는 리사이클러 뷰에 들어갈 data를 준비하고, 어댑터와 데이터들을 연결하여 리사이클러 뷰를 최종적으로 구현할 수 있다.

### fragment 구현
Fragment를 구현하기 위해 필요한 준비물
1. Fragment를 구현할 view(xml 파일) - `fragment_temp.xml`
2. Fragment만을 구현한 view를 다룰 kotlin 파일 - `TempFragment.kt`
3. Fragment를 담을 view(xml 파일) - `activity_fragment.xml`
4. Fragment를 담고 있는 view를 다룰 kotlin 파일 - `FragmentActivity.kt`
5. build.gradle에 아래 코드 입력
```
implementation "androidx.fragment:fragment-ktx:1.3.2"
```
***
Fragment를 다룰 kotlin파일에서는 아래와 같이 간단하게 처리해주면 된다.
```
override fun onCreateView(  
    inflater: LayoutInflater,  
  container: ViewGroup?,  
  savedInstanceState: Bundle?  
): View? {  
    return inflater.inflate(R.layout.fragment_temp, container, false)  
}
```
***
Fragment를 포함하고 있는 뷰에서는 아래 코드와 같이 Fragment를 띄워줄 수 있습니다.
```
override fun onCreate(savedInstanceState: Bundle?) {  
    ...
    connectFragment()  
    ...
}  
  
private fun connectFragment() {  
    val tempFragment = TempFragment()  
    val transaction = supportFragmentManager.beginTransaction()  
    transaction.add(R.id.fragment_temp, tempFragment)  
    transaction.commit()  
}
```
* * *
**Fragment의 주요 생명 주기**
- **onCreateView()** : fragment의 뷰를 그리는 시점. 뷰 관련된 초기화들은 여기에서 이루어진다.
- **onViewCreated()** : 뷰가 만들어지고 난 후이며, 이때부터 fragment가 activity에 온전히 접근할 수 있다.
- **onPause()** : 사용자 프래그먼트를 떠나는 첫 신호로 유지하려는 데이터가 있을 경우 여기서 저장한다.
