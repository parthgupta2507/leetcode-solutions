import math
from typing import Optional, List

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def nodesBetweenCriticalPoints(self, head: Optional[ListNode]) -> List[int]:
        # A list with fewer than 3 nodes cannot have any critical points
        if not head or not head.next or not head.next.next:
            return [-1, -1]
        
        first_cp_index = -1
        last_cp_index = -1
        min_distance = math.inf
        
        prev = head
        curr = head.next
        index = 1  # 0-indexed position of curr
        
        while curr.next:
            # Check if current node is a local minimum or local maximum
            if (curr.val > prev.val and curr.val > curr.next.val) or \
               (curr.val < prev.val and curr.val < curr.next.val):
                
                # If this is the first critical point found
                if first_cp_index == -1:
                    first_cp_index = index
                else:
                    # Update minimum distance using adjacent critical points
                    min_distance = min(min_distance, index - last_cp_index)
                
                # Update the position of the last seen critical point
                last_cp_index = index
            
            # Move pointers forward
            prev = curr
            curr = curr.next
            index += 1
            
        # If fewer than 2 critical points were found
        if min_distance == math.inf:
            return [-1, -1]
        
        # Maximum distance is always between the first and last critical points
        max_distance = last_cp_index - first_cp_index
        
        return [min_distance, max_distance]