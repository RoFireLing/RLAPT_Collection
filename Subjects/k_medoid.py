import random

import numpy as np


def cal_distance(x, y):
    # 欧式距离
    return np.sqrt(sum(np.square(x - y)))


def k_medoid(data, cluster_num, func_of_dis):
    indexs = list(range(len(data)))
    random.shuffle(indexs)
    init_centroids_index = indexs[:cluster_num]
    centroids = data[init_centroids_index, :]
    sample_target = []
    if_stop = False
    while not if_stop:
        if_stop = True
        classify_points = [[centroid] for centroid in centroids]
        sample_target = []
        for sample in data:
            distances = [func_of_dis(sample, centroid) for centroid in centroids]
            cur_level = np.argmin(distances)
            sample_target.append(cur_level)
            classify_points[cur_level].append(sample)
        for i in range(cluster_num):
            distances = [func_of_dis(point_1, centroids[i]) for point_1 in classify_points[i]]
            now_distances = sum(distances)
            for point in classify_points[i]:
                distances = [func_of_dis(point_1, point) for point_1 in classify_points[i]]
                new_distance = sum(distances)
                if new_distance < now_distances:
                    now_distances = new_distance
                    centroids[i] = point
                    if_stop = False

    return sample_target, centroids


def cal_sse(data, tag, center):
    sse = 0
    for idx, d in enumerate(data):
        sse += cal_distance(d, center[tag[idx]]) ** 2
    return sse


def cal_dis_mat(center):
    dis_mat = []
    for i in range(len(center)):
        tmp = []
        for j in range(len(center)):
            tmp.append(cal_distance(center[i], center[j]))
        dis_mat.append(tmp)
    return dis_mat
