#
#
./create-test-topics.sh
java -jar ./target/kafka-tester-1.0.jar load-test-1.yaml > load-test_200kb.out
#
./create-test-topics.sh
java -jar ./target/kafka-tester-1.0.jar load-test-2.yaml >> load-test_200kb.out
#
./create-test-topics.sh
java -jar ./target/kafka-tester-1.0.jar load-test-3.yaml >> load-test_200kb.out
#
./create-test-topics.sh
java -jar ./target/kafka-tester-1.0.jar load-test-4.yaml >> load-test_200kb.out
#
./create-test-topics.sh
java -jar ./target/kafka-tester-1.0.jar load-test-5.yaml >> load-test_200kb.out
#
./create-test-topics.sh
java -jar ./target/kafka-tester-1.0.jar load-test-6.yaml >> load-test_200kb.out
