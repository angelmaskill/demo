http://www.cnblogs.com/peida/p/3180129.html

Multimap

Guava的Multimap就提供了一个方便地把一个键对应到多个值的数据结构。让我们可以简单优雅的实现上面复杂的数据结构，让我们的精力和时间放在实现业务逻辑上，而不是在数据结构上，下面我们具体来看看Multimap的相关知识点。

调用Multimap.get(key)会返回这个键对应的值的集合的视图（view），没有对应集合就返回空集合。对于ListMultimap来说，这个方法会返回一个List，对于SetMultimap来说，这个方法就返回一个Set。修改数据是通过修改底层Multimap来实现的。

Multimap也支持一系列强大的视图功能： 
　　
	1.asMap把自身Multimap<K, V>映射成Map<K, Collection<V>>视图。这个Map视图支持remove和修改操作，但是不支持put和putAll。严格地来讲，当你希望传入参数是不存在的key，而且你希望返回的是null而不是一个空的可修改的集合的时候就可以调用asMap().get(key)。（你可以强制转型asMap().get(key)的结果类型－对SetMultimap的结果转成Set，对ListMultimap的结果转成List型－但是直接把ListMultimap转成Map<K, List<V>>是不行的。）
　　2.entries视图是把Multimap里所有的键值对以Collection<Map.Entry<K, V>>的形式展现。
　　3.keySet视图是把Multimap的键集合作为视图
　　4.keys视图返回的是个Multiset，这个Multiset是以不重复的键对应的个数作为视图。这个Multiset可以通过支持移除操作而不是添加操作来修改Multimap。
　　5.values()视图能把Multimap里的所有值“平展”成一个Collection<V>。这个操作和Iterables.concat(multimap.asMap().values())很相似，只是它返回的是一个完整的Collection。

尽管Multimap的实现用到了Map，但Multimap<K, V>不是Map<K, Collection<V>>。因为两者有明显区别：
　　
	1.Multimap.get(key)一定返回一个非null的集合。但这不表示Multimap使用了内存来关联这些键，相反，返回的集合只是个允许添加元素的视图。
　　2.如果你喜欢像Map那样当不存在键的时候要返回null，而不是Multimap那样返回空集合的话，可以用asMap()返回的视图来得到Map<K, Collection<V>>。（这种情况下，你得把返回的Collection<V>强转型为List或Set）。
　　3.Multimap.containsKey(key)只有在这个键存在的时候才返回true。
　　4.Multimap.entries()返回的是Multimap所有的键值对。但是如果需要key-collection的键值对，那就得用asMap().entries()。
　　5.Multimap.size()返回的是entries的数量，而不是不重复键的数量。如果要得到不重复键的数目就得用Multimap.keySet().size()。


Multimap的实现

　　Multimap提供了丰富的实现，所以你可以用它来替代程序里的Map<K, Collection<V>>，具体的实现如下：
　　Implementation            Keys 的行为类似       　　　Values的行为类似
　　ArrayListMultimap         HashMap                   　　ArrayList
　　HashMultimap               HashMap                  　　 HashSet
　　LinkedListMultimap        LinkedHashMap*              LinkedList*
　　LinkedHashMultimap      LinkedHashMap                LinkedHashSet
　　TreeMultimap                TreeMap                          TreeSet
　　ImmutableListMultimap  ImmutableMap                 ImmutableList
　　ImmutableSetMultimap  ImmutableMap                 ImmutableSet

　　
　　以上这些实现，除了immutable的实现都支持null的键和值。
　　1.LinkedListMultimap.entries()能维持迭代时的顺序。

　　2.LinkedHashMultimap维持插入的顺序，以及键的插入顺序。
　　要注意并不是所有的实现都正真实现了Map<K, Collection<V>>！（尤其是有些Multimap的实现为了最小话开销，使用了自定义的hash table）