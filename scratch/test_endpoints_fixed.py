import urllib.request
import urllib.error
import time

endpoints = [
    "deliverys",
    "favoritess",
    "users"
]

base_url = "http://localhost:8080/api/v1/admin/"

for ep in endpoints:
    url = base_url + ep
    try:
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        response = urllib.request.urlopen(req, timeout=10)
        print(f"[OK] {url} - Status: {response.status}")
    except urllib.error.HTTPError as e:
        print(f"[FAILED] {url} - Status: {e.code}")
    except Exception as e:
        print(f"[ERROR] {url} - {str(e)}")
