## 2.5 应用
#### 2.5.1 将各种数据排序
###### 1) 交易事务
###### 2) 指针排序
###### 3) 不可变的键
###### 4) 廉价的交换
###### 5) 多种排序方法
###### 6) 多键数组
###### 7) 使用比较器实现优先队列
###### 8) 稳定性
如果一个排序算法能够保留数组中重复元素的相对位置, 则可以被称为稳定的 . 

#### 2.5.2 我们该使用哪种排序算法

###### 各排序算法的性能特点
|  算法         | 是否稳定|  原地排序   | 时间复杂度  |  空间复杂度   | 备注  |
|  ----        | ----     |  ----     | ----       |  ----  | ----  |
| 选择排序      | 否       | 是       | N²          | 1  |  |
| 插入排序      | 是       | 是       | N - N²      | 1  | 取决于输入元素的排列 |
| 希尔排序      | 否       | 是       | N^3/2 ?     | 1  | 希尔排序的时间复杂度和递增序列相关 |
| 快速排序      | 否       | 是       | NlgN        | lgN  | 运行效率由概率保证 , 空间为每次递归都需要记录切分元素 |
| 三向快速排序  | 否        | 是       | N - NlgN    | lgN  | 输入元素有大量重复时 , 时间复杂度为N |
| 归并排序      | 是       | 否       | NlgN        | N  | 空间复杂度为辅助数组 |
| 堆排序        | 否       | 是       | NlogN        | 1  | 适合优先队列和不稳定的数组取极值 |

快速排序是最快的通用排序 ,  大多数情况为最佳选择 .  
稳定性很重要 , 空间又不是问题 , 那么归并排序就很适合 .  

###### 1) 将原始类型数据排序 
跳过引用类型之接排序 , 可以节省大量通过引用访问数字的成本
###### 2) Java系统库的排序算法
Arrays.sort() : 
1.  每种原始数据类型都实现了一个不同的排序方法(含有三向切分快速排序)
2.  一个适用于所有实现了Comparable接口的数据类型的排序方法 (归并排序)
3.  一个适用于实现了比较器Comparator的数据类型的排序方法  (归并排序) 

#### 2.5.3 问题的规约
###### 1) 找出重复元素
###### 2) 排名
###### 3) 优先队列
###### 4) 中位数和顺序统计 
例如 : 基于切分选择中位数

#### 2.5.4 排序应用一览
###### 1) 商业计算
###### 2) 信息搜索
###### 3) 运筹学
###### 4) 事件驱动模型
###### 5) 数值计算
###### 6) 组合搜索

