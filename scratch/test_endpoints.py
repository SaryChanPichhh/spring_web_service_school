import urllib.request
import urllib.error
import time

endpoints = [
    "coupons",
    "couponassignments",
    "deliveries",
    "exchangerates",
    "faqs",
    "favorites",
    "feedbacks",
    "freedeliveryassignments",
    "menus",
    "permissions",
    "restaurants",
    "reviews",
    "saledetails",
    "saleheaders",
    "users"
]

base_url = "http://localhost:8080/api/v1/admin/"

print("Waiting for server to start...")
time.sleep(5)

success = 0
failed = 0

for ep in endpoints:
    url = base_url + ep
    try:
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        response = urllib.request.urlopen(req, timeout=5)
        print(f"[OK] {url} - Status: {response.status}")
        success += 1
    except urllib.error.HTTPError as e:
        print(f"[FAILED] {url} - Status: {e.code}")
        failed += 1
    except Exception as e:
        print(f"[ERROR] {url} - {str(e)}")
        failed += 1

print(f"\nTest Summary: {success} Passed, {failed} Failed.")
