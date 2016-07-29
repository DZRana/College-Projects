from multiprocessing import Process, Lock
from multiprocessing.sharedctypes import Value, Array
from random import randint
import time
def min(i,j):
	if i < j:
		return i
	else:
		return j
def FloydWarshall(w, costArr, n, k, i):
	t = i
	for t in range (t,n,k):
		for i in range (t,n,k):
			for j in range (t,n,k):
				w[i*j] = min(w[i*j],(w[i*k]+w[k*j]))
		costArr[t] = w[i*j]

if __name__ == '__main__':
	n = int(input("Enter n: "))
	k = int(input("Enter k: "))
	w = Array('i', [(randint(0,n-1)) for x in range (n*n)])
	costArr = Array('i',[0 for x in range(n)])
	start = time.time()

	for i in range (k):
		p = Process(target = FloydWarshall, args = (w, costArr, n, k, i))
		p.start()
		p.join()
	if k == 2 or k == 4:
		print (costArr[:])
	end = time.time()
	print("Total Time (sec): ", end-start)

# PROBLEM 1

#Enter n: 12
#Enter m: 4
#Enter k: 2
#[4, 3, 4, 1]
#Total Time (sec):  0.06309318542480469

#Enter n: 64
#Enter m: 8
#Enter k: 3
#[9, 7, 6, 1, 8, 9, 5, 9]
#Total Time (sec):  0.11078810691833496

#Enter n: 4096
#Enter m: 16
#Enter k: 5
#Total Time (sec):  1.3732919692993164

#Enter n: 16384
#Enter m: 32
#Enter k: 7
#Total Time (sec):  9.591964960098267

#---------------------------------------------------------------------------

# PROBLEM 2

#Enter n: 8
#Enter k: 2
#[4, 5, 4, 5, 4, 5, 4, 5]
#Total Time (sec):  0.06571078300476074

#Enter n: 32
#Enter k: 4
#[2, 10, 22, 7, 2, 10, 22, 7, 2, 10, 22, 7, 2, 10, 22, 7, 2, 10, 22, 7, 2, 10, 22, 7, 2, 10, 22, 7, 2, 10, 22, 7]
#Total Time (sec):  0.19720959663391113

#Enter n: 2048
#Enter k: 8
#Total Time (sec):  3349.4236359596252

