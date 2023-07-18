def database_mergeJoin_(table1: list, table2: list, table1_join_index: int, table2_join_index: int):
    """JOIN TWO TABLES TABLE1 AND TABLE2 REPRESENTED AS A LIST, join as in the relational database context of join
    super_list1 = [
    [4, "a"],
    [1, "h"],
    [1, "z"],
    [2, "b"],
    [1, "c"],
    [3, "d"]
]
super_list2 = [
    [4, "d"],
    [2, "f"],
    [1, "g"],
    [3, "h"]
]
    >>> database_mergeJoin_(super_list1, super_list2,0,0)
    [[1, 'h', 'g'], [1, 'z', 'g'], [1, 'c', 'g'], [2, 'b', 'f'], [3, 'd', 'h'], [4, 'a', 'd']]
    >>> database_mergeJoin_(super_list1, super_list2,0,1)
    []
   

    """
    result = []
    sorted1 = sorted(table1, key=lambda x: x[table1_join_index])
    sorted2 = sorted(table2, key=lambda x: x[table2_join_index])
    index1, index2 = 0, 0
    
    while index1 < len(table1) and index2 < len(table2):
        if sorted1[index1][table1_join_index] == sorted2[index2][table2_join_index]:
            tempindex1 = index1
            #if no duplicates -> will loop only one time and conitnue
            #if duplicates -> check every possible combination by moving at first index of table 2 and then table 1
            while tempindex1 < len(table1) and sorted1[tempindex1][table1_join_index] == sorted1[index1][table1_join_index]:
                tempindex2 = index2
                while tempindex2 < len(table2) and sorted2[tempindex2][table2_join_index] == sorted2[index2][table2_join_index]:
                    #dont include the same join value twice in the result value
                    temp1 = sorted1[tempindex1].copy() #deep copy se we dont destroy data from sorted2 table
                    temp2 = sorted2[tempindex2].copy()
                    temp2.remove(sorted2[tempindex2][table2_join_index])

                    temp1.extend(temp2)
                    result.append(temp1)
                    
                    tempindex2 += 1
                tempindex1 += 1
            index1 = tempindex1
            index2 = tempindex2
        elif str(sorted1[index1][table1_join_index]) < str(sorted2[index2][table2_join_index]):
            index1 += 1
        else:
            index2 += 1

    return result



super_list1 = [
    [4, "a"],
    [1, "h"],
    [1, "z"],
    [2, "b"],
    [1, "c"],
    [3, "d"]
]
super_list2 = [
    [4, "d"],
    [2, "f"],
    [1, "g"],
    [3, "h"]
]
"""
#Paradeigma diafaneiwn
super_list1 = [
    [0, "a"],
    [5, "b"],
    [3, "j"]
]

super_list2 = [
    [3, "g"],
    [7, "f"],
    [0, "j"]
]
"""

#should return empty list 
print(database_mergeJoin_(super_list1,super_list2,1,0),"\n")
#should be equivelent if we join(A,A)
print(database_mergeJoin_(super_list1,super_list2,0,0),"\n")
print(database_mergeJoin_(super_list1,super_list2,1,1))

#python -m doctest -v <name_of_file>.py to run test

