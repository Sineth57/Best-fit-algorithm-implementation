package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        //memory blocks
        int[] memoryBlocks = {100, 200, 300, 400, 500};
        System.out.println("Sizes of memory blocks: ");

        //displaying the sizes of memory blocks
        for (int i=0; i<memoryBlocks.length; i++){
            System.out.println("Memory Block " + (i+1) + ":" + memoryBlocks[i] + " KB");

        }

        Scanner scanner = new Scanner(System.in);

        //get the number of jobs as an input
        System.out.println("Enter the number of jobs: ");
        int numJobs = scanner.nextInt();
        Queue<Integer> jobQueue = new LinkedList<>();

        //input memory sizes for the jobs
        for (int i=0; i<numJobs; i++){
            System.out.println("Memory required for each job " + (i+1) + ": ");
            int memoryRequired = scanner.nextInt();
            jobQueue.add(memoryRequired);
        }

        //convert memory blocks array to a list for dynamic updates
        List<Integer> reminingMemory = new ArrayList<>();
        for (int block : memoryBlocks){
            //adding memory block sizes to the list
            reminingMemory.add(block);
        }

        //arranaging the jobs using best fit algorithm

        //initializing the job ID starting from 1
        int jobId = 1;

        //continue untill all the jobs in the queue are arranged accordingly
        while (!jobQueue.isEmpty()){
            //get the memory size of the next job
            int memoryRequired = jobQueue.poll();
            //variable to store the index of best fit memory block
            int bestIndex = -1;

            //find smallet memory block that fits the job
            for (int i =0; i<reminingMemory.size(); i++){
                if (reminingMemory.get(i) >= memoryRequired && (bestIndex == -1 || reminingMemory.get(i) < reminingMemory.get(bestIndex))) {
                    bestIndex = i; // update the index of the best fit block
                }
            }

            //allocate memory to jobs
            if (bestIndex!= -1){
                System.out.println("Job" + jobId + "allocated to block" + (bestIndex + 1));
                // reduce the size of the allocated block by the memory used
                reminingMemory.set(bestIndex, reminingMemory.get(bestIndex) - memoryRequired);
            }else{
                System.out.println("Job " + jobId + " could not be allocated memory.");
            }
            //moving to the next job
            jobId++;
        }

        //display final status of jobs
        System.out.println("Updated memory blocks");

        for (int i = 0; i < reminingMemory.size(); i++) {
            System.out.println("Memory Block " + (i + 1) + ": " + reminingMemory.get(i) + " KB remaining");
        }

        scanner.close();
    }
}