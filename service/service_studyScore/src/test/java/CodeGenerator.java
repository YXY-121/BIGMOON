import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;


import java.util.*;


/**
 * @author
 * @since 2018/12/13
 */
public class CodeGenerator {

    @Test
    public void run() {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("C:\\Users\\yxy\\eclipse-workspace\\zongce2\\service\\service_studyScore" + "/src/main/java");

        gc.setAuthor("yxy");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖

        //UserServie
        gc.setServiceName("%sService");    //去掉Service接口的首字母I

        gc.setIdType(IdType.AUTO); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/zongce?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("service_studyScore"); //模块名
        //包  com.atguigu.eduservice
        pc.setParent("com.yxy");
        //包  com.atguigu.eduservice.controller
        pc.setController("controller");
        pc.setEntity("bean");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
//       选择数据库的表名
        strategy.setInclude("service_admin");

        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    @Test
    public void test(){
        TreeNode root_right=new TreeNode(3,null,new TreeNode(5));
        TreeNode root_left=new TreeNode(2,new TreeNode(4),null);
        TreeNode root=new TreeNode(1,root_left,root_right);
      //  List<List<Integer>> s=levelOrder(root);
         int A[]=new int[3];
         A[0]=0; A[1]=1; A[2]=1;
        List<Boolean> booleans = prefixesDivBy5(A);
        System.out.println(booleans);
    }
    public List<Boolean> prefixesDivBy5(int[] A) {
        StringBuffer sb=new StringBuffer();
        List<Boolean> answer=new ArrayList();
        for(int i=0;i<A.length;i++){
            sb.append(A[i]);
            String value=sb.toString();
            String v[]=new String[100];
            if(i==0){
                v[i]=A[i]+"";
                sb.append(".");
            }
            else{
                sb.append(".");
                v= value.split(".");
            }
            int sum=0;

            for(int j=A.length-1;j>=0;j--){
                sum= (int) (sum+ Math.pow(2,j));
            }
            System.out.println(sum);
          answer.add(sum%5==0);




        }
        return answer;
    }


     public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 @Test
  public void testListnode(){
        ListNode dummy=new ListNode(-1);

     for(int i=1;i<=5;i++){
         ListNode node=new ListNode(i);
         ListNode tmp=dummy;
         while(tmp.next!=null){
             tmp=tmp.next;
         }
         tmp.next=node;
     }
     //  printList(dummy.next);

   ListNode listNode = reverseKGroup(dummy.next, 2);
    printList(listNode);

 }
  public void printList(ListNode head){
      ListNode row=head;
      while(row!=null)
      {
          System.out.println(row.val);
          row=row.next;
      }
      System.out.println("over");
  }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy=new ListNode(-1);
        ListNode now=head;
        ListNode reversehead=null,reversetail=null;
        ListNode tmpNow=null,go=null;
        int reverseNum=0,bugou=0;
        ListNode begin=null;
        while(now!=null){
            tmpNow=now;
            begin=now;          //todo 没有！
            for(int i=0;i<k;i++){  //todo k不一样！

                if(now.next==null){//不够长了 直接返回整个链表{
                    ListNode tmp=dummy.next;
                    while(tmp.next!=null){
                        tmp=tmp.next;
                    }
                    tmp.next=tmpNow;
                    return dummy.next;
                }
                now=now.next;


            }
            reversetail=begin;//todo 不一样！ 这他妈往后改的 前面肯定不同啊
            reversehead=reverse(reversetail,k); //todo k不一样！
            reverseNum++;
          //  printList(reversehead);



            if(reverseNum==1)
                dummy.next=reversehead;//第一次就用这个来连接
            else
            {//将每个反转段连接起来
                ListNode tmp=dummy.next;
                while(tmp.next!=null){
                    tmp=tmp.next;
                }
                tmp.next=reversehead;
            }
      //      printList(dummy.next);
          //  now=go;
        }
        return dummy.next;
    }
    public ListNode reverse(ListNode head,int n){//想反转2个节点 n=1 这里写错了 应该是向前肘
        ListNode prev=null,now=head;
        while(now!=null&&n>0){
            ListNode next=now.next;
            now.next=prev;
            prev=now;
            now=next;
            n--;

        }
        return prev;
    }



////////////////////////////////////////////////////////
    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode dummy=new ListNode(-1);
        ListNode now=head;
        ListNode reversehead=null,reversetail=null;
        ListNode tmpNow=null;
        int reverseNum=0,bugou=0;
        while(now!=null){
            tmpNow=now;
            for(int i=0;i<k-1;i++){
                if(now.next==null){//不够长了 直接返回整个链表{
                    ListNode tmp=dummy.next;
                    while(tmp.next!=null){
                        tmp=tmp.next;
                    }
                    tmp.next=tmpNow;
                    return dummy.next;
                }
                now=now.next;

            }

            reversetail=now;
            reversehead=reverse1(reversetail,k-1);
            reverseNum++;


            if(reverseNum==1)
                dummy.next=reversehead;//第一次就用这个来连接
            else
            {//将每个反转段连接起来
                ListNode tmp=dummy.next;
                while(tmp.next!=null){
                    tmp=tmp.next;
                }
                tmp.next=reversehead;
            }
        }
        return dummy.next;
    }
    public ListNode reverse1(ListNode head,int n){//想反转2个节点 n=1
        ListNode prev=null,now=head;
        while(now!=null&&n>0){
            ListNode next=now.next;
            now.next=prev;
            prev=now;
            now=next;
            n--;
        }
        return prev;
    }
}

