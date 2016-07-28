from multiprocessing import Process, Lock
from multiprocessing.sharedctypes import Value, Array
from random import randint
import time
def f(m, a, b):
    for row in range(0,n-1,4):
        temp = 0
        for col in range(0,n-1,1):
            temp = temp + ((m[row][col])*(a[col]))
        b[row] = temp
        time.sleep(1/n)
def g(m, a, b):
    for row in range(1,n-1,4):
        temp = 0
        for col in range(0,n-1,1):
            temp = temp + ((m[row][col])*(a[col]))
        b[row] = temp
        time.sleep(1/n)
def h(m, a, b):
    for row in range(2,n-1,4):
        temp = 0
        for col in range(0,n-1,1):
            temp = temp + ((m[row][col])*(a[col]))
        b[row] = temp
        time.sleep(1/n)
def k(m, a, b):
    for row in range(3,n-1,4):
        temp = 0
        for col in range(0,n-1,1):
            temp = temp + ((m[row][col])*(a[col]))
        b[row] = temp
        time.sleep(1/n)
if __name__ == '__main__':
    n = int(input("Enter N: "))
    m = [Array('i', [(randint(0,n-1)) for x in range (n)])]
    a = Array('i', [(randint(0,n-1)) for x in range (n)])
    b = Array('i', [0 for x in range (n)])
    start = time.time()
    p = Process(target = f, args = (m,a,b))
    q = Process(target = g, args = (m,a,b))
    r = Process(target = h, args = (m,a,b))
    s = Process(target = k, args = (m,a,b))

    p.start()
    q.start()
    r.start()
    s.start()

    p.join()
    q.join()
    r.join()
    s.join()
    end = time.time()
    print("Total time (sec): ", (end-start))