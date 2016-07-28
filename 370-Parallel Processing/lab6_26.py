from multiprocessing import Process, Lock
from multiprocessing.sharedctypes import Value, Array
from random import randint
import time
def rankSort1(data1, data2, rankArr, offset, offVal, result):
    for i in range (0, (int)((len(data1)-1)/2)-1, 1):
        rank = 0
        for j in range (0, len(data2)-1, 1):
            if data1[i] > data2[j]:
                rank = rank + 1
        rankArr.append(rank)
        for k in range (offset[rank], len(offset), 1):
            with offVal.get_lock():
                offVal.value = offset[k]
                offVal.value += 1
            offset[k] = offVal.value
    for m in range (0, len(rankArr) - 1, 1):
        result[m + (rankArr[m])] = data1[m]
    for n in range (0, len(offset) - 1, 1):
        result[n + (offset[n])] = data2[n]

def rankSort2(data1, data2, rankArr, offset, offVal, result):
    for i in range (((int)((len(data1)-1)/2)), (len(data1) - 1), 1):
        rank = 0
        for j in range (0, len(data2)-1, 1):
            if data1[i] > data2[j]:
                rank = rank + 1
        rankArr.append(rank)
        for k in range (offset[rank], len(offset), 1):
            with offVal.get_lock():
                offVal.value = offset[k]
                offVal.value += 1
            offset[k] = offVal.value
    for m in range (0, len(rankArr) - 1, 1):
        result[m + (rankArr[m])] = data1[m]
    for n in range (0, len(offset) - 1, 1):
        result[n + (offset[n])] = data2[n]

def rankSort3(data1, data3, rankArr, offset, offVal, result):
    for i in range (((int)((len(data1)-1)/2)), (len(data1) - 1), 1):
        rank = 0
        for j in range (0, len(data2)-1, 1):
            if data1[i] > data2[j]:
                rank = rank + 1
        rankArr.append(rank)
        for k in range (offset[rank], len(offset), 1):
            with offVal.get_lock():
                offVal.value = offset[k]
                offVal.value += 1
            offset[k] = offVal.value
    for m in range (0, len(rankArr) - 1, 1):
        result[m + (rankArr[m])] = data1[m]
    for n in range (0, len(offset) - 1, 1):
        result[n + (offset[n])] = data2[n]
def rankSort4(data1, data3, rankArr, offset, offVal, result):
    for i in range (((int)((len(data1)-1)/2)), (len(data1) - 1), 1):
        rank = 0
        for j in range (0, len(data2)-1, 1):
            if data1[i] > data2[j]:
                rank = rank + 1
        rankArr.append(rank)
        for k in range (offset[rank], len(offset), 1):
            with offVal.get_lock():
                offVal.value = offset[k]
                offVal.value += 1
            offset[k] = offVal.value
    for m in range (0, len(rankArr) - 1, 1):
        result[m + (rankArr[m])] = data1[m]
    for n in range (0, len(offset) - 1, 1):
        result[n + (offset[n])] = data2[n]

if __name__ == '__main__':
    data1 = Array('i',[2,15,17,29,35])
    data2 = Array('i',[1,3,6,8,9,12,16,18,22,25,30,32,36,40])
    data3 = Array('i',[1,2,2,3,6,14,14,16,16,17,20,27,30,35,35])
    data4Unsorted = [(randint(0,100)) for x in range (20)]
    data4 = Array('i', [data4Unsorted.sort()])
    data5Unsorted = [(randint(0,100)) for x in range (2000)]
    data5 = Array('i', [data5Unsorted.sort()])
    data6Unsorted = [(randint(0,100)) for x in range (100)]
    data6 = Array('i', [data6Unsorted.sort()])
    data7Unsorted = [(randint(0,100)) for x in range (50000)]
    data7 = Array('i', [data7Unsorted.sort()])
    rankArr = []
    offset = [0 for i in range(len(data2))]
    offVal = Value('i', 0)
    result = Array('i',[0 for i in range((len(data1))+(len(data2)))])
    #FOR DATA3 AND DATA4, SORT FIRST THEN DO THE RANKING STUFF
    
    #start = time.time()
    #Test data (1) p = Process(target = rankSort1, args = (data1, data2, rankArr, offset, offVal, result))
    #Test data (1) q = Process(target = rankSort2, args = (data1, data2, rankArr, offset, offVal, result))
    #Test data (2) p = Process(target = rankSort3, args = (data1, data3, rankArr, offset, offVal, result))
    #Test data (2) q = Process(target = rankSort4, args = (data1, data3, rankArr, offset, offVal, result))
    
    p.start()
    q.start()
    
    p.join()
    q.join()
    print(result[:])
    #r.join()
    #s.join()
    #end = time.time()
    #print("Total time (sec): ", (end-start))
    #print("Smallest: ", smallest.value)
    #print("Total Count: ", totalCount.value)