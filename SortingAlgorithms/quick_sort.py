def quick_sort(array):
	length = len(array)
	if length < 2:
		return array
	pivot = array[length//2]
	less = [x for x in array if x < pivot]
	greater = [x for x in array if x > pivot]
	equal = [x for x in array if x == pivot]
	
	return quick_sort(less) + equal + quick_sort(greater)