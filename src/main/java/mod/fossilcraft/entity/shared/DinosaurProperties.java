package mod.fossilcraft.entity.shared;

public class DinosaurProperties {


    public final Diet diet;
    public final Morph[] morphs;
    public final float minSize;
    public final float maxSize;
    public final int babyAge;
    public final int adultAge;

    private DinosaurProperties(Builder builder) {
        this.diet = builder.diet;
        this.morphs = builder.morphs;
        this.minSize = builder.minSize;
        this.maxSize = builder.maxSize;
        this.babyAge = builder.babyAge;
        this.adultAge = builder.adultAge;
    }

    public static class Builder {
        private Diet diet;
        private Morph[] morphs;
        private float minSize;
        private float maxSize;
        private int babyAge;
        private int adultAge;


        public Builder diet(Diet diet) {
            this.diet = diet;
            return this;
        }

        public Builder morphs(Morph[] morphs) {
            this.morphs = morphs;
            return this;
        }

        public Builder size(float minSize, float maxSize) {
            this.minSize = minSize;
            this.maxSize = maxSize;
            return this;
        }

        public Builder ageStages(int baby, int adult) {
            this.babyAge = baby;
            this.adultAge = adult;
            return this;
        }

        public DinosaurProperties build() {
            return new DinosaurProperties(this);
        }
    }
}
