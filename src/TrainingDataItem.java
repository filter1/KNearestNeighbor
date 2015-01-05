// represents one 'row' of the given data.
public class TrainingDataItem {
	private int buying, maint, doors, persons, lug_boot, safety;
	private String targetClass;

	public TrainingDataItem(String str) {

		String[] a = str.split(",");
		
		switch(a[0]) {
			case "v-high":
				this.buying = 12;
				break;
			case "high":
				this.buying = 9;
				break;
			case "med":
				this.buying = 6;
				break;
			case "low":
				this.buying = 3;
				break;					
		}
		   
		switch(a[1]) {
			case "v-high":
				this.maint = 12;
				break;
			case "high":
				this.maint = 9;
				break;
			case "med":
				this.maint = 6;
				break;
			case "low":
				this.maint = 3;
				break;					
		}
		
		switch(a[2]) {
			case "5-more":
				this.doors = 12;
				break;
			case "4":
				this.doors = 9;
				break;
			case "3":
				this.doors = 6;
				break;
			case "2":
				this.doors = 3;
				break;			
		}

		switch(a[3]) {
			case "more":
				this.persons = 12;
				break;
			case "4":
				this.persons = 8;
				break;
			case "2":
				this.persons = 4;
				break;		
		}
		
		switch(a[4]) {
			case "big":
				this.lug_boot = 12;
				break;
			case "med":
				this.lug_boot = 8;
				break;
			case "small":
				this.lug_boot = 4;
				break;		
		}
		
		switch(a[5]) {
			case "high":
				this.safety = 12;
				break;
			case "med":
				this.safety = 8;
				break;
			case "low":
				this.safety = 4;
				break;		
		}
		
		this.targetClass = a[6];
	}

	public double distance(TrainingDataItem item) {
		double res = 0;
		res += Math.pow(this.buying - item.buying, 2);
		res += Math.pow(this.maint - item.maint, 2);
		res += Math.pow(this.doors - item.doors, 2);
		res += Math.pow(this.persons - item.persons, 2);
		res += Math.pow(this.lug_boot - item.lug_boot, 2);
		res += Math.pow(this.safety - item.safety, 2);
		return res;
	}
	
	public int getBuying() {
		return buying;
	}

	public int getMaint() {
		return maint;
	}

	public int getDoors() {
		return doors;
	}

	public int getPersons() {
		return persons;
	}

	public int getLug_boot() {
		return lug_boot;
	}

	public int getSafety() {
		return safety;
	}

	public String getTargetClass() {
		return targetClass;
	}
}