from multiprocessing import Process, Lock
from multiprocessing.sharedctypes import Value, Array
from random import randint
import time
def f(m, smallest, totalCount):
    for row in range(0,n-1,4):
        for col in range(0,n-1,1):
			if m[row][col] < smallest:
				smallest = m[row][col]
				with m.get_lock():
					totalCount = 0
			if m[row][col] == smallest:
				with m.get_lock():
					totalCount += 1
def g(m,smallest, totalCount):
    for row in range(1,n-1,4):
        for col in range(0,n-1,1):
			if m[row][col] < smallest:
				smallest = m[row][col]
				with m.get_lock():
					totalCount = 0
			if m[row][col] == smallest:
				with m.get_lock():
					totalCount += 1
def h(m, smallest, totalCount):
    for row in range(2,n-1,4):
        for col in range(0,n-1,1):
			if m[row][col] < smallest:
				smallest = m[row][col]
				with m.get_lock():
					totalCount = 0
			if m[row][col] == smallest:
				with m.get_lock():
					totalCount += 1
def k(m,smallest, totalCount):
    for row in range(3,n-1,4):
        for col in range(0,n-1,1):
			if m[row][col] < smallest:
				smallest = m[row][col]
				with m.get_lock():
					totalCount = 0
			if m[row][col] == smallest:
				with m.get_lock():
					totalCount += 1
if __name__ == '__main__':
    n = int(input("Enter N: "))
    m = [Array('i', [(randint(0,n-1)) for x in range (n)])]
	
	start = time.time()
    p = Process(target = f, args = (m,smallest, totalCount))
    q = Process(target = g, args = (m,smallest, totalCount))
    r = Process(target = h, args = (m,smallest, totalCount))
    s = Process(target = k, args = (m,smallest, totalCount))

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
	print("Smallest: ", smallest.value)
	print("Total Count: ", totalCount.value)