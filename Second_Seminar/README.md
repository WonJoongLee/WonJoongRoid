# 2주차
### 구동영상
<p align="center">
<img src="https://user-images.githubusercontent.com/57510192/115836891-f7681880-a452-11eb-9ded-acb7c1216069.gif" width="300px">
</p>

2주차에서는 `Fragment`, `RecyclerView`, `Data & UI`, `List`를 배웠습니다.
<br>
### 폴더 구조
<p align="center">
<img src="https://user-images.githubusercontent.com/57510192/115832679-20d27580-a44e-11eb-9529-e1e99fb0450f.PNG" width="200px">
</p>
- adapters : 앱 내에는 두 가지의 리사이클러뷰가 있는데, 각 리사이클러 뷰들의 어댑터가 있습니다.<br>
- data : data class들이 정의되어 있습니다.<br>
- ui : Activity들과 Fragment ui가 정의되어 있습니다.
<br>

## Level 1 - 리사이클러뷰 구현 + Fragment
리사이클러뷰는 크게 `adapter`, `data class`, activity에서 adapter를 설정해주는 코드로 이루어져 있습니다.
<br>

### adapter 구현 부분
adapter는 기본적으로 `onCreateViewHolder`, `onBindViewHolder`, `getItemCount`로 이루어져 있고, 	추가적으로 Custom `ViewHolder` class를 정의합니다. 

RepositoryAdapter.kt
```kotlin
class RepositoryAdapter(private val data: List<RepositoryInfo>) :  
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {  
  
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {  
        val layoutInflater = LayoutInflater.from(parent.context)  
        val binding: ItemRepositoryBinding =  
            DataBindingUtil.inflate(layoutInflater, R.layout.item_repository, parent, false)  
        return RepositoryViewHolder(binding)  
    }  
  
    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {  
        holder.bind(data[position])  
    }  
  
    override fun getItemCount() = data.size  
  
  class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :  
        RecyclerView.ViewHolder(binding.root) {  
        fun bind(repositoryInfo: RepositoryInfo) {  
            binding.apply {  
  repo = repositoryInfo  
            }  
  }  
    }  
}
```

SigningActivity.kt
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
위 코드로 xml에 있던 recyclerview에 adapter를 연결해줄 수 있습니다.
<br>
### textview의 길이가 너무 길어질 경우에 대한 처리
```xml
android:ellipsize="end"  
android:maxLines="1"
```
<br>

### Fragment 이용하기
기존에 Fragment에 대해 잘 몰랐는데 이번 기회에 조금 알 수 있게 되었습니다.
1. 먼저 Fragment xml과 kotlin 파일을 생성하고 정의합니다.
```kotlin
override fun onCreateView(  
    inflater: LayoutInflater,  
  container: ViewGroup?,  
  savedInstanceState: Bundle?  
): View {  
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follower, container, false)  
    setFollowerRv() // 이 코드는 RecyclerView 설정하는 코드여서 꼭 들어가야 하는 코드가 아닙니다.
    return binding.root  
}
```
Fragment 내에서는 `onCreateView`를 overriding해야 합니다. 

2. 그 후 Fragment를 띄울 Activity에서 Fragment 설정을 합니다.
```kotlin
private fun connectFragment() {  
    val followerFragment = FollowerFragment()  
    val transaction = supportFragmentManager.beginTransaction()  
    transaction.add(R.id.fragment_follower, followerFragment)  
    transaction.commit()  
}
```

<br>
<br>

## Level 2 - GridLayoutManager + Multi ViewType
<p align="center">
<img src="https://user-images.githubusercontent.com/57510192/115835140-ecac8400-a450-11eb-9039-700268446616.PNG" width="350px">
</p>

### GridLayout
GridLayoutManager를 사용하기 위해서는 기존 Recyclerview에서 두 가지 attribute만 추가해주면 됩니다.
`app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"`, `app:spanCount="3"`입니다. 
첫 번째 속성은 Grid로 view를 보여주겠다는 것이고, spanCount는 한 줄에 몇 개의 view가 띄워질 것인지에 대한 속성입니다.
<br>

### Recyclerview에 여러 view 보여주기
카카오톡과 같은 곳에서 흔하게 사용할 것 같은 Multi Viewtype을 활용하여 GridLayout에 다양한 view를 띄웠습니다.
1. 먼저 RecyclerView Adapter에서 `getItemViewType`을 overriding하여 넘겨줄 viewtype을 설정합니다.
```kotlin
override fun getItemViewType(position: Int): Int {  
    return when(data[position].viewType){  
        FollowerInfo.NORMAL_CONTENT -> FollowerInfo.NORMAL_CONTENT  
  FollowerInfo.AD_CONTENT -> FollowerInfo.AD_CONTENT  
		else -> throw RuntimeException("View Type Error at getItemViewType")  
    }  
}
```
2. `onCreateViewHolder`에서는 넘어오는 viewType에 따라 viewholder를 알맞게 처리해줍니다.
```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {  
    val layoutInflater = LayoutInflater.from(parent.context)  
    return when (viewType) {  
        FollowerInfo.NORMAL_CONTENT -> {  
            val binding: ItemFollowerBinding =  
                DataBindingUtil.inflate(layoutInflater, R.layout.item_follower, parent, false)  
            FollowerViewHolder(binding)  
        }  
        FollowerInfo.AD_CONTENT -> {  
            val binding: ItemAdvertisementBinding = DataBindingUtil.inflate(  
                layoutInflater,  
  R.layout.item_advertisement,  
  parent,  
 false  )  
            AdViewHolder(binding)  
        }  
        else -> throw RuntimeException("View Type Error at onCreateViewHolder")  
    }  
}
```
3. `onBindViewHolder()`에서도 마찬가지로 viewType에 따라 목적에 맞는 viewholder와 연결하여 binding처리를 해줍니다.
```kotlin
override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {  
    val obj = data[position]  
    when (obj.viewType) {  
        FollowerInfo.NORMAL_CONTENT -> {  
            (holder as FollowerViewHolder).bind(obj)  
        }  
        FollowerInfo.AD_CONTENT -> {  
            (holder as AdViewHolder).bind()  
        }  
    }  
}
```
4. viewHolder class를 목적에 맞게 여러 개를 만들어야 합니다.
```kotlin
class FollowerViewHolder(private val binding: ItemFollowerBinding) :  
    RecyclerView.ViewHolder(binding.root) {  
    fun bind(followerInfo: FollowerInfo) {  
        binding.apply {  
  follower = followerInfo  
        }  
  }  
}  
  
class AdViewHolder(private val binding: ItemAdvertisementBinding) :  
    RecyclerView.ViewHolder(binding.root) {  
    fun bind() {  
        binding.apply {  
  adContent = "광고!"  
  }  
  }  
}
```
- FollowerViewHolder는 github follower들을 보여줄 viewholder입니다.
- AdViewHolder는 광고 view를 보여줄 viewholder입니다.

