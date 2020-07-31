package com.example.codehead.criminalintent.database;

public class DiaryDbSchema {
    public static final class DiaryTable{
        public static final String NAME="diary";

        public static final class Cols{
            public static final String id="id";
            public static final String date="data";
            public static final String officer_id="officer_id";
            public static final String type="type";
            public static final String species_id="species_id";
            public static final String local_name ="local_name";
            public static final String previously_seen  ="previously_seen";
            public static final String gender  ="gender";
            public static final String age  ="age";
            public static final String count  ="count";
            public static final String growth_status  ="growth_status";
            public static final String health_status  ="health_status";
            public static final String death_cause  ="death_cause";
            public static final String description  ="description";
            public static final String area_name  ="area_name";
            public static final String latitude  ="latitude";
            public static final String longitude  ="longitude";
            public static final String photo_link  ="photo_link";
            public static final String video_link  ="video_link";

        }

    }
}
