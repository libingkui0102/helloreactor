# helloreactor
spring5 reactor3使用实例

- MultiplicationTable 打印99乘法表
- Fibonacci数列
- 组合Flux
  - concat 组合两个Flux, 有先后顺序
  - merge 同时合并两个flux中的元素
  - zip　按顺序配对合并两个元素，无法配对(其中一个flux消耗完)时则终止
  - combineLatest 组合两个flux中最新的元素，某个flux中的数据会有重复利用的情况
- 批处理
  - buffer 一次处理多条数据
  - window 把一个数据流分成多个数据流来处理
  - group 把数据分类进行处理
  
  

