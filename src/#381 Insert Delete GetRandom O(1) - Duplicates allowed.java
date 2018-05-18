import java.util.*;

class RandomizedCollection {

    Map<Integer, Set<Integer>> map;

    List<Integer> nums;

    Random random;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        nums = new ArrayList<>();
        random = new Random();
    }

    public static void main(String[] args) {
        // Init an empty collection.
        RandomizedCollection collection = new RandomizedCollection();

        collection.insert(0);
        collection.remove(0);
        collection.insert(-1);
        collection.remove(0);
        collection.getRandom();
        collection.getRandom();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        final int num_index = nums.size();
        nums.add(val);

        boolean contain = map.containsKey(val);
        if (!contain) map.put(val, new HashSet<>());
        map.get(val).add(num_index);
        return !contain;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        final Set<Integer> positions = map.get(val);
        /*当前val 在数组里的一个位置*/
        final Integer current_position = positions.iterator().next();
        /*移除map中 要删除元素的位置记录*/
        positions.remove(current_position);
        /*数组中最后一个元素的位置*/
        final int last_number_position = nums.size() - 1;
        /*数组中的最后一个元素*/
        final Integer last_number = nums.get(last_number_position);
        /*将最后一个元素 放到要删除的位置上*/
        nums.set(current_position, last_number);
        /*为最后一个元素，修改位置记录*/
        map.get(last_number).add(current_position);
        map.get(last_number).remove(last_number_position);
        /*从数组中删除最后一个 多余的 要被删除的元素*/
        nums.remove(last_number_position);
        /*如果这个数字已经没用记录了，从map中删除它*/
        if (map.get(val).isEmpty()) map.remove(val);

        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        if (nums.isEmpty()) return -1;
        return nums.get(random.nextInt(nums.size()));
    }
}

