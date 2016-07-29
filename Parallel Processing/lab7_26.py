from multiprocessing import Process, Lock
from multiprocessing.sharedctypes import Value, Array
from random import randint
import time

def countNums(data, i, k, m, n, locks, count):
	local = [0 for x in range(m)]
	t = i
	for t in range (t, n, k):
		for v in range (0, m, 1):
			if data[t] == v + 1:
				local[v] += 1
	for j in range (m):
		with locks[j]:
			count[j] += local[j]

if __name__ == '__main__':
	n = int(input("Enter n: "))
	m = int(input("Enter m: "))
	k = int(input("Enter k: "))
	data1 = Array('i',[1,2,3,1,4,1,2,1,3,2,3,3])
	count = Array('i',[0 for x in range (m)])
	data234 = Array('i',[(randint(0,m)) for x in range(n)])

	locks = []
	for j in range(m):
		tmp = Lock()
		locks.append(tmp)

	start = time.time()

	for i in range (k):
		if k == 2:
			p = Process(target = countNums, args = (data1, i, k, m, n, locks, count))
		else:
			p = Process(target = countNums, args = (data234, i, k, m, n, locks, count))
		p.start()
		p.join()
	end = time.time()
	if k == 2 or k == 3:
		print(count[:])
	print("Total Time (sec): ", end-start)

	