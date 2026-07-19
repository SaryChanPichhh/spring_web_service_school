import urllib.request
import urllib.error

endpoints = [
    "deliveries",
    "favorites"
]

base_url = "http://localhost:8080/api/v1/admin/"

for ep in endpoints:
    url = base_url + ep
    try:
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        response = urllib.request.urlopen(req)
        print(f"[OK] {url}")
    except urllib.error.HTTPError as e:
        print(f"[FAILED] {url} - Status: {e.code}")
        print("Response body:")
        print(e.read().decode('utf-8'))
    except Exception as e:
        print(f"[ERROR] {url} - {str(e)}")
