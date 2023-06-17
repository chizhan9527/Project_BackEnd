import unittest
import requests
import UseCase
from BeautifulReport import BeautifulReport

suite = unittest.TestSuite()    # 类的实例化！！！要加括号才是实例化
ids = UseCase.ids


class ArticleTest(unittest.TestCase):  # 调用unittest
    def setUp(self) -> None:  # 前置条件
        # 登录接口
        re = requests.post(url='http://localhost:8081/Login',
                           data={'password': '1', 'phone': '1'})  # 发送接口请求
        global token  # 设置一个全局变量
        token = re.json()['data']['tokenValue']  # 给全局变量赋值，值为接口响应的token的值

    def tearDown(self) -> None:          # 后置条件
        print("执行结束")  # 打印标示用例执行结束

    def test_get_article(self):
        '''
        获取文章
        '''
        # 查询接口
        for i in ids:
            res = requests.get('http://localhost:8081/article/'+i,
                               headers={'Content-Type': 'application/json', "satoken": token})
            a = res.text
            print(a)  # 打印结果


if __name__ == '__main__':
    suite.addTest(ArticleTest('test_get_article'))  # 添加测试用例
    # 添加用例描述
    result = BeautifulReport(suite)
    result.report(filename='测试报告', description='测试deafult报告',
                  log_path='./report')
    # unittest.main()
