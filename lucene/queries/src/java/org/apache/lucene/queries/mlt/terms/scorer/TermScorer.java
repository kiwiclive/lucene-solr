/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.lucene.queries.mlt.terms.scorer;

import java.io.IOException;
import java.util.Map;

import org.apache.lucene.index.NumericDocValues;
import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.TermStatistics;
import org.apache.lucene.search.similarities.Similarity;

/**
 * This class has the responsibility of calculating the score for a term in the source text.
 * The score will measure how much interesting the term for the given source field text:
 * - term stats local to the source field text
 * - field stats global to the index
 */
public interface TermScorer {
  float score(String fieldName, CollectionStatistics globalFieldStats, TermStatistics globalTermStats, float localTermFrequency) throws IOException;

  Similarity.SimWeight getSimilarityStats(String fieldName, CollectionStatistics globalFieldStats, TermStatistics globalTermStats, float localTermFrequency) throws IOException;

  }
