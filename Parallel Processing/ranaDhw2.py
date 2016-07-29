from os import getpid
from multiprocessing import Pool
from time import time
def isPrime(n):	
    if (n <= 1):
        return False
    for i in range(2,int(n**0.5)+1):
        if n%i==0:
            return False
    return True
def countPrimes (start):
    rng = 800000
    formatStr = 'process {} processing range ({} {})'
    print(formatStr.format(getpid(), start, start+rng))
    return sum([1 for i in range(start, start+rng) if isPrime(i)])
if __name__ == '__main__':
    n = input("Enter number of nodes: ")
    p = Pool(8)	
    starts = [0]
    t1 = time()
    print(p.map(countPrimes, starts))
    t2 = time()
    p.close()
    print('time take: {} seconds:'.format(t2-t1))

