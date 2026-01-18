import requests

url = "https://gitee.com/api/v5/repos/kewk/NekoBox-Float-Window/releases/latest"

try:
    response = requests.get(url, timeout=10)
    if response.status_code == 200:
        data = response.json()
        print(f"Gitee 最新标题: {data['name']}")
        print(f"版本号: {data['tag_name']}")
    else:
        print(f"获取失败，状态码: {response.status_code}")
except Exception as e:
    print(f"网络连接错误: {e}")