import unittest
import requests
from BeautifulReport import BeautifulReport

suite = unittest.TestSuite()    # 类的实例化！！！要加括号才是实例化


class ArticleTest(unittest.TestCase):  # 调用unittest
    def setUp(self) -> None:  # 前置条件
        # 登录接口
        re = requests.post(url='http://localhost:8081/Login',
                           data={'password': '123456', 'phone': '16696738903'})  # 发送接口请求
        global token  # 设置一个全局变量
        token = re.json()['data']['tokenValue']  # 给全局变量赋值，值为接口响应的token的值

    def tearDown(self) -> None:          # 后置条件
        print("执行结束")  # 打印标示用例执行结束

    def test_change1(self):
        # 关注接口
        print("更改权限")
        payload = {"as_id": 52, "user_id": 14, "manager_id": 29}
        print(payload)
        res = requests.put(url='http://localhost:8081/changeRank',
                               params=payload)
        a = res.text
        print(a)

    def test_change2(self):
        # 关注接口
        print("更改权限")
        payload = {"as_id": 52, "user_id": 21, "manager_id": 29}
        print(payload)
        res = requests.put(url='http://localhost:8081/changeRank',
                               params=payload)
        a = res.text
        print(a)

    def test_delete(self):
        # 删除接口
        payload = {
            "as_id": 52, "user_id": 14, "manager_id": 29}
        print(payload)
        res = requests.delete('http://localhost:8081/manager',
                              params=payload)
        a = res.text
        print(a)


if __name__ == '__main__':
    suite.addTest(ArticleTest('test_change1'))  # 添加测试用例
    suite.addTest(ArticleTest('test_change2'))  # 添加测试用例
    suite.addTest(ArticleTest('test_delete'))  # 添加测试用例
    # 添加用例描述
    result = BeautifulReport(suite)
    result.report(filename='Sys_Test_case_fun_023系统测试报告', description='Sys_Test_case_fun_023系统测试报告',
                  log_path='./report')
    # unittest.main()
