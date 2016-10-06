# scm
## 进销存管理系统
### 项目描述
SCM手机进销存管理系统是一款通专业的手机进销存管理系统，该系统是集采购、销售及库存管理于一体，它囊括了企业日常经营管理的全过程，方便了手机销售商的管理和决策，提升企业的发展。          
#### 系统主要包括以下各个模块     
1.系统管理与维护,主要有:           
公司信息设置、用户与权限设置、登入与退出、软件帮助、年终结算、查看日志,备份/恢复数据库    
2.基础数据管理： 在基本设置中可以对数据字典（系统参数）、商品信息、供货商、客户、员工、仓库、操作员、会员等基本信息进行管理    
3.进货管理：可以进行采购进货、采购退货及相关单据和账务查询    
4.销售管理：可以进行商品销售、顾客退货及相关单据、账务查询    
5.库存管理：可以进行库存调拔、报损报溢、库存盘点、库存报警查询    
6.统计与分析:提供商品采购统计、业务员采购统计、库存成本统计、商品销售统计、业务员销售统计、商品销售排行、供应商供货统计、商品销售统计等    
7.日常管理：提供对供应商供货情况及合同等信息、客户的消费及合同等、营业费用、换班等进行管理    
#### 系统主要采用技术：
ajax/jquery/easyUI：实现前端界面及与服务端交互。     
jscharts:前端图形报表工具，无需安装第三方插件、有柱状、饼状、线形等多种图形，支持二维三维图形展现。   
spring mvc :web层MVC实现框架，基于注解，简单易用、快速开发。    
spring :框架整合、基于AOP、IOC 实现各层及组件解偶、业务层事务管理等等    
ibatis(mybatis):持久层框架，基于SQL，简单易用，效律较好。。。         
#### 开发时间与计划
项目完成时间大概4-5个月，其中需求分析4-5周，系统设计4-5周，编码4周，测试及bug修复4周，不包括上线与维护等）     
#### 项目开发、部署环境
操作系统：开发机器： win7     
数据库：mysql5.6      
应用服务器：tomcat7       
开发工具：Eclipse、maven3.2.3           
软件环境：tomcat+spring mvc +spring+mybatis+easyUI(ajax、jquery)+jscharts+mysql
### 项目特点
该项目整体采用ssm(spring+springmvc+mybatis)架构，前台使用jQuery,easyUI,ajax,jscharts技术实现,持久层使用传统的mybatis配置xml和一种通用的Mapper两种方式实现。
### 责任描述
本人参与系统设计与讨论、文档评审及部分模块的设计与实现。负责:    
1.负责基础模块及采购模块的相关设计(主要完成后台业务逻辑层、持久层的代码编写)。      
2.完成通用模块（分页等）、基础数据管理、进货管理等功能的编码实现（包括单元测试）。   
3.配合测试部门完成系统部署、修复测试人员发现的各种bug。    
4.指导开发工程师根据设计文档完成模块的开发工作。    
5.完成布置的临时任务，包括协助其解决开发出现的各种问题。     

### 项目中难点
1.采购管理模块中商品采购栏目一共有两个表格同时提交，上面是采购基本信息，下面是要选择的部门，选择部门表前面商品管理模块已经完成这里只要调用就好。如何完成两个表格同时提交。     
前台利用Jquery中的$('#ff').form()提交表单，只是完成上面中数据的提交，对于下面dataGride表格中的数据var rows = $("#dg").datagrid("getRows");得到对象中的数据//把对象转成JSON格式的字符串rows = JSON.stringify(rows);
```
//提交采购到服务端
											$('#ff')
													.form(
															'submit',
															{
																url : '${proPath}/buyOrder/insert.action',
																onSubmit : function(
																		param) {
																	param.rows = rows;
																	return true;

																},
																success : function(
																		data) {
																	//自己处理返回的信息  
																	alert("恭喜你，提交成功!");
																}
															});

										}
```
发送请求：http://localhost:8080/scm/buyOrder/insert.action?supId=1&shId=&boDate=2016-06-22&boPayable=500000&boPaid=150000&boArrears=350000&boOriginal=1232435&boAttn=12&boOperator=12&boRemark=11&rows=%5B%7B%22goodsId%22%3A%222%22%2C%22goodsName%22%3A%22note5%22%2C%22goodsUnit%22%3A%22%E9%83%A8%22%2C%22goodsType%22%3Anull%2C%22goodsColor%22%3A%225%22%2C%22bodAmount%22%3A%221%22%2C%22bodBuyPrice%22%3A%2222.00%22%2C%22bodTotalPrice%22%3A22%2C%22bodImeiList%22%3A%223333%22%7D%2C%7B%22goodsId%22%3A%221%22%2C%22goodsName%22%3A%22note4%22%2C%22goodsUnit%22%3A%22%E9%83%A8%22%2C%22goodsType%22%3Anull%2C%22goodsColor%22%3A%221%22%2C%22bodAmount%22%3A%221%22%2C%22bodBuyPrice%22%3A%2222.00%22%2C%22bodTotalPrice%22%3A22%2C%22bodImeiList%22%3A%223333%22%7D%5D
```
后台接收:
@RequestMapping(value="/insert")
	@ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
	public Object insert(BuyOrder buyOrder, String rows){
		System.out.println("---action.buyOrder:"+buyOrder);
		System.out.println("---action.rows:"+rows);
		//提供json格式数据转java对象
		ObjectMapper mapper = new ObjectMapper();
		int i = 0;
		try {
			BuyOrderDetail[] buyOrderDetails = mapper.readValue(rows, BuyOrderDetail[].class);
			buyOrder.setBuyOrderDetails(Arrays.asList(buyOrderDetails));
			i =buyOrderService.insertBuyOrder(buyOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
```
BuyOrder实体中加入public List<BuyOrderDetail> buyOrderDetails;            
2.通过关键字分页查询,多条件查询       
封装一个page实体，在page实体中加入一个private T paramEntity;//多条件查询属性,前台(rasyUI+Jquery)会发送请求,自动携带查询参数到后台。
```
$('#supAddress').searchbox({ 
			searcher:function(value,name){ 
				alert("supAddress:"+value); 
				alert("supName:"+$('#supName').val());
				$('#dg').datagrid('load',{
					supName: '%'+$('#supName').val()+'%',
					supAddress:'%'+value+'%'		
				});					
			}, 
			prompt:'' 
		});
前台发送supAddress,supName到后台自动封装，然后查询返回json数据
//通过关键字分页查询,多条件查询
    @RequestMapping("/selectPageUseDyc")
    @ResponseBody
    public Object selectPageUseDyc(Page<Supplier> page,Supplier supplier){
        page.setParamEntity(supplier);
        Page p = supplierService.selectPageUseDyc(page);
        return p.getPageMap();
    }
    
    @Override
	public Page<Supplier> selectPageUseDyc(Page<Supplier> page) {
		// TODO Auto-generated method stub
		page.setList(supplierMapper.selectPageListUseDyc(page));
		page.setTotal(supplierMapper.selectPageCountUseDyc(page));
		return page;
	}
  
  <!-- 通过多条件分页查询，返回数据集 -->
    <select id="selectPageListUseDyc" parameterType="page" resultMap="supplierResultMap" >
        select * from supplier
        <where>
            <if test="paramEntity.supName!=null">and sup_name like #{paramEntity.supName}</if>
            <if test="paramEntity.supAddress!=null">and sup_address like #{paramEntity.supAddress}</if>
        </where>
        limit #{start},#{rows}
    </select>

xml中sql语句
    <!-- 通过多条件分页查询，返回总记录数 -->
    <select id="selectPageCountUseDyc" parameterType="page" resultType="Integer">
        select count(1) from supplier
        <where>
            <if test="paramEntity.supName!=null">and sup_name like #{paramEntity.supName}</if>
            <if test="paramEntity.supAddress!=null">and sup_address like #{paramEntity.supAddress}</if>
        </where>
    </select>
```     
3.利用反射技术完成对业务逻辑层BaseService的抽取   
```
//服务器启动就加载
public class BaseServiceImpl<T> implements BaseService<T> {

	protected BaseMapper<T> baseMapper;

	@Autowired
	protected SupplierMapper supplierMapper;

	@Autowired
	protected AccountMapper accountMapper;

	@Autowired
	protected GoodsMapper goodsMapper;

	@Autowired
	protected BuyOrderDetailMapper buyOrderDetailMapper;

	@Autowired
	protected  BuyOrderMapper buyOrderMapper;

	@Autowired
	protected  AccountRecordsMapper accountRecordsMapper;

	@Autowired
	protected SysParamMapper sysParamMapper;
	@PostConstruct
	// 在构造方法后，初化前执行
	private void initBaseMapper() throws Exception {
		// 完成以下逻辑，需要对研发本身进行命名与使用规范
		// this关键字指对象本身，这里指的是调用此方法的实现类（子类）
		System.out.println("=======this :" + this);
		System.out.println("=======父类基本信息：" + this.getClass().getSuperclass());
		System.out.println("=======父类和泛型的信息："
				+ this.getClass().getGenericSuperclass());

		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// 获取第一个参数的class
		Class clazz = (Class) type.getActualTypeArguments()[0];
		System.out.println("=======class:" + clazz);
		// 转化为属性名（相关的Mapper子类的引用名）Supplier supplierMapper
		String localField = clazz.getSimpleName().substring(0, 1).toLowerCase()
				+ clazz.getSimpleName().substring(1) + "Mapper";
		System.out.println("=======localField:" + localField);
		// getDeclaredField:可以使用于包括私有、默认、受保护、公共字段，但不包括继承的字
		// 段
		Field field = this.getClass().getSuperclass()
				.getDeclaredField(localField);
		System.out.println("=======field:" + field);
		System.out.println("=======field对应的对象:" + field.get(this));
		Field baseField = this.getClass().getSuperclass()
				.getDeclaredField("baseMapper");

		System.out.println("=======baseField:" + baseField);
		System.out.println("=======baseField对应的对象:" + baseField.get(this));
		// field.get(this)获取当前this的field字段的值。例如：Supplier对象中，baseMapper所指向的对象为其子类型SupplierMapper对象，子类型对象已被spring实例化于容器中
		baseField.set(this, field.get(this));
		System.out.println("========baseField对应的对象:" + baseMapper);

	}

	@Override
	public Integer insert(T entity) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.insert(entity);
	}

	@Override
	public int update(T entity) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.update(entity);
	}

	@Override
	public int delete(T entity) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.delete(entity);
	}

	@Override
	public int deleteList(String[] pks) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.deleteList(pks);
	}

	@Override
	public T select(T entity) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.select(entity);
	}

	@Override
	public Page<T> selectPage(Page<T> page) {
		page.setList(baseMapper.selectPageList(page));
		page.setTotal(baseMapper.selectPageCount(page));
		return page;
	}

	@Override
	public Page<T> selectPageUseDyc(Page<T> page) {
		// TODO Auto-generated method stub
		page.setList(baseMapper.selectPageListUseDyc(page));
		page.setTotal(baseMapper.selectPageCountUseDyc(page));
		return page;
	}

}
```
封装以后统一用baseMapper调用BaseService中的增删改查方法，使得以后实现的接口不用在调用这些方法.     
4.使用servlet做缓存实现，实现前台页面数据的显示.
```
public class SysInitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        Map<String,Object> sysParamMap =new HashMap<String,Object>();

        Map<String,Object> supTypeMap =new HashMap<String,Object>();
        supTypeMap.put("1", "一级供应商");
        supTypeMap.put("2", "二级供应商");
        supTypeMap.put("3", "三级供应商");

        sysParamMap.put("supType", supTypeMap);

        Map<String,Object> goodsColorMap =new HashMap<String,Object>();
        goodsColorMap.put("1","红色");
        goodsColorMap.put("2","绿色");
        goodsColorMap.put("3","蓝色");
        sysParamMap.put("goodsColor", goodsColorMap);

        this.getServletContext().setAttribute("sysParam", sysParamMap);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
    }
}
//前台
 {
				field : 'supType',
				title : '供应商类型',
				width : 100,
				formatter: function(value,row,index){
					var str = "${applicationScope.sysParam.supType}";			
					return valueToText(str,value);				
				}
        web.xml中配置tomcat启动就加载     
        <servlet>
		<servlet-name>SysInitServlet</servlet-name>
		<servlet-class>com.xhp.scm.servlet.SysInitServlet</servlet-class>
		<load-on-startup>2</load-on-startup>
	</servlet>
        
```




