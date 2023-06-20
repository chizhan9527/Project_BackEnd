import unittest
import requests
from BeautifulReport import BeautifulReport

suite = unittest.TestSuite()  # 类的实例化！！！要加括号才是实例化


class FollowTest(unittest.TestCase):  # 调用unittest
    def tearDown(self) -> None:  # 后置条件
        print("执行结束")  # 打印标示用例执行结束

    def test_follow(self):
        # 关注接口
        print("测试关注接口")
        payload = {"userId": "4", "followUserId": "15"}
        print(payload)
        res = requests.post(url='http://localhost:8081/follow',
                            data=payload)
        a = res.text
        print(a)

    def test_getFollow(self):
        # 返回关注列表接口
        print("测试返回关注列表接口")
        payload = {"id": "4"}
        print(payload)
        res = requests.get('http://localhost:8081/follow/' + "4",
                           params=payload)
        a = res.text
        print(a)

    def test_followOrNot(self):
        # 是否关注接口
        print("测试是否关注接口")
        payload = {"userId": "4", "followUserId": "8"}
        print(payload)
        res = requests.get('http://localhost:8081/follow/or/not/' + "8",
                           params=payload)
        a = res.text
        print(a)

    def test_commonFollow(self):
        # 共同关注接口
        print("测试共同关注接口")
        payload = {"id": "4", "userId": "10"}
        print(payload)
        res = requests.get('http://localhost:8081/follow/common/' + "4",
                           params=payload)
        a = res.text
        print(a)


if __name__ == '__main__':
    suite.addTest(FollowTest('test_follow'))
    suite.addTest(FollowTest('test_getFollow'))
    suite.addTest(FollowTest('test_followOrNot'))
    suite.addTest(FollowTest('test_commonFollow'))
    # 添加用例描述
    result = BeautifulReport(suite)
    result.report(filename='Follow模块系统测试报告', description='SystemTest',
                  log_path='./report')
    # unittest.main()
