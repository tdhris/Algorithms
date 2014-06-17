def merge_sort(array):
	length = len(array)
	if length < 2:
		return array
	middle = length // 2
	left = merge_sort(array[:middle])
	right = merge_sort(array[middle:])
	
	return merge(left, right)

def merge(left, right):
	length = len(left) + len(right)
	result = []
	i, j = 0, 0
	for k in range(length):
		if len(left) > i and len(right) > j:
			if left[i] <= right[j]:
				smaller = left[i]
				i += 1
			else:
				smaller = right[j]
				j += 1
		elif len(left) == i:
			smaller = right[j]
			j += 1
		
		else:
			smaller = left[i]
			i += 1
		
		result.append(smaller)
	return result
		
	
	