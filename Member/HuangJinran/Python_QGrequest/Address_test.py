import  unittest
from Sendhttp import SendHttp
import Common
class qgaddressList(unittest.TestCase):

    def setUp(self):
        self.url= "/fgadmin/address/list"

    def test_address_by_Login(self):
        user = {"phoneArea": "86", "phoneNumber": "20000000000", "password": "netease123"}
        result=SendHttp().sent_get_bycookies(self.url,Common.getcookies(user))
        print(result)